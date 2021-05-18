package sample.API.Seat;

import org.json.JSONObject;
import sample.model.Seat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Класс API для мест для отправки PUT зароса на сервер
 * @author damir
 */
public class SeatPut {

    public static boolean editSeat(Seat seat) throws IOException {

        final String url = "http://localhost:8080/seats/" + seat.getId();
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("cost", seat.getCost());
        json.put("number", seat.getNumber());
        json.put("seatType", seat.getSeatType());
        json.put("carId", seat.getCarId());
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("PUT");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        return httpClient.getResponseCode() == 200;
    }
}
