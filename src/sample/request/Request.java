package sample.request;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Request {

    private final String url = "http://localhost:8080/";
    private final HttpURLConnection httpClient = (HttpURLConnection) new URL(this.url).openConnection();

    public Request() throws IOException {
    }

    public String requestGET() throws IOException {
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }

    public void requestPOST(String name, String surname, Integer postalCode, String city, String birthday, String street) throws IOException {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("surname", surname);
        json.put("postalCode", postalCode.toString());
        json.put("city", city);
        json.put("birthday", birthday);
        json.put("street", street);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
}
