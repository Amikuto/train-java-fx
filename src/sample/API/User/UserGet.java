package sample.API.User;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Класс API для пользователей для отправки GET зароса на сервер
 * @author damir
 */
public class UserGet {

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }

    public static boolean checkPassword(String login, Integer password) throws IOException {
        String url = "http://localhost:8080/users/password/" + login;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("password", password);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine().equals("true");
    }

    public String userGetByLogin(String login) throws IOException {
        String url = "http://localhost:8080/users/" + login;
        return sendGetRequest(url);
    }
}
