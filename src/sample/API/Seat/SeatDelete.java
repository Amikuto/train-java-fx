package sample.API.Seat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс API для мест для отправки DELETE зароса на сервер
 * @author damir
 */
public class SeatDelete {

    public static boolean deleteSeat(Long seatId) throws IOException {
        String url = "http://localhost:8080/seats/" + seatId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        return httpClient.getResponseCode() == 200;
    }
}
