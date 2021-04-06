package sample.request.GET.Station;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class StationDelete {

    public static Integer deleteStation(String stationId) throws IOException {
        String url = "http://localhost:8080/stations/" + Integer.parseInt(stationId);
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return Integer.parseInt(stationId);
        } else {
            return 0;
        }
    }
}
