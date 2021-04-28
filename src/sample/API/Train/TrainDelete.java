package sample.API.Train;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrainDelete {

    public static Long deleteTrain(Long trainId) throws IOException {
        String url = "http://localhost:8080/trains/" + trainId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return trainId;
        } else {
            return 0L;
        }
    }
}
