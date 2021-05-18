package sample.API.Train;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс API для поездов для отправки DELETE зароса на сервер
 * @author damir
 */
public class TrainDelete {

    public static boolean deleteTrain(Long trainId) throws IOException {
        String url = "http://localhost:8080/trains/" + trainId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        return httpClient.getResponseCode() == 200;
    }
}
