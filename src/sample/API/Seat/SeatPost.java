package sample.API.Seat;

import org.json.JSONObject;
import sample.model.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SeatPost {

//    public static void addNewSeat(Integer cost, Integer number, String seatType, Integer carId) throws IOException {
    public static boolean addNewSeat(Seat seat) throws IOException {
        final String url = "http://localhost:8080/seats/" + seat.getCarId();
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("cost", seat.getCost());
        json.put("number", seat.getNumber());
        json.put("seatType", seat.getSeatType());
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        return httpClient.getResponseCode() == 200;

//        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));

//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
    }
}
