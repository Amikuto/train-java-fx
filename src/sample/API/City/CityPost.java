package sample.API.City;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CityPost {

    public static Integer addNewCity(String name) throws IOException {
        final String url = "http://localhost:8080/cities";
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("name", name);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

//        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));
        return httpClient.getResponseCode();
//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
    }
}
