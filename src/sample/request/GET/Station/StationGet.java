package sample.request.GET.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class StationGet {

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }

    public String getAllStationsByCityName(String cityName) throws IOException {
        String url = "http://localhost:8080/stations/" + URLEncoder.encode(cityName, StandardCharsets.UTF_8) + "/city";
        return sendGetRequest(url);
    }

    public String stationGetAll() throws IOException {
        String url = "http://localhost:8080/stations";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }
}
