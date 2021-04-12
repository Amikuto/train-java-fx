package sample.request.GET.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

    public String trainGetByDepAndArrStationAndDepDate(String depCity, String arrCity, String depDate) throws IOException {
        String url = "http://localhost:8080/trains/" + URLEncoder.encode(depCity, StandardCharsets.UTF_8) + "/" + URLEncoder.encode(arrCity, StandardCharsets.UTF_8) + "/" + depDate;
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
