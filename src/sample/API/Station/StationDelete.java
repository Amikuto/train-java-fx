package sample.API.Station;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс API для станций для отправки DELETE зароса на сервер
 * @author damir
 */
public class StationDelete {

    public static boolean deleteStation(Long stationId) throws IOException {
        String url = "http://localhost:8080/stations/" + stationId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        return httpClient.getResponseCode() == 200;
    }
}
