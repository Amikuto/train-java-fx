package sample.API.User;

import org.json.JSONObject;
import sample.model.Car;
import sample.model.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Класс API для пользователей для отправки POST зароса на сервер
 * @author damir
 */
public class UserPost {

    public static boolean addNewUser(String fullName, String login, String email, String password) throws IOException {
        final String url = "http://localhost:8080/users";
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("fullName", fullName);
        json.put("email", email);
        json.put("login", login);
        json.put("password", password);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        return httpClient.getResponseCode() == 200;
    }
}
