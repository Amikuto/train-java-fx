package sample.request.GET.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StationGet {

    public StationGet() throws IOException {
    }

    public String stationGetAllCities() throws IOException {
        String url = "http://localhost:8080/stations/city";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
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
