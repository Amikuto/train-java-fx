package sample.request.GET.Car;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CarPost {

    public static void addNewCar(Integer number, String carType, Integer trainId, String carClass) throws IOException {
        final String url = "http://localhost:8080/cars";
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("number", number);
        json.put("ctype", carType);
        json.put("tid", trainId);
        json.put("cclass", carClass);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));

//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
    }
}
