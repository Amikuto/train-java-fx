package sample.request.GET.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class TrainGet {

    public String trainGetAll() throws IOException {
        String url = "http://localhost:8080/trains";
        return sendGetRequest(url);
    }

    public String trainGetById(Long id) throws IOException {
        String url = "http://localhost:8080/trains/"+id;
        return sendGetRequest(url);
    }

    public String trainGetByDepAndArrStation(Integer depStationId, Integer arrStationId) throws IOException {
        String url = "http://localhost:8080/trains/" + depStationId + "/" + arrStationId + "/stations";
        return sendGetRequest(url);
    }

    public String trainGetByDepAndArrStationAndDepDate(Integer depStationId, Integer arrStationId, String depDate) throws IOException {
        String url = "http://localhost:8080/trains/" + depStationId + "/" + arrStationId + "/" + depDate;
        return sendGetRequest(url);
    }

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }
}
