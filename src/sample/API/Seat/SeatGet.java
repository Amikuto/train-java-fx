package sample.API.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс API для мест для отправки GET зароса на сервер
 * @author damir
 */
public class SeatGet {

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }

    public String seatGetByCarId (Long trainId) throws IOException {
        String url = "http://localhost:8080/cars/" + trainId + "/trains";
        return sendGetRequest(url);
    }
}
