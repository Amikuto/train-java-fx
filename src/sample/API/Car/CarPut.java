package sample.API.Car;

import org.json.JSONObject;
import sample.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CarPut {

    public static boolean editCar(Car car) throws IOException {
        final String url = "http://localhost:8080/cars/" + car.getId();
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("number", car.getNumber());
        json.put("ctype", car.getType());
        json.put("tid", car.getTrainId());
        json.put("cclass", car.getCarClass());
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("PUT");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        return httpClient.getResponseCode() == 200;
    }
}
