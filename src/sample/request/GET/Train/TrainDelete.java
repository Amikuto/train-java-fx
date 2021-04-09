package sample.request.GET.Train;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrainDelete {

    public static Integer deleteTrain(String trainId) throws IOException {
        String url = "http://localhost:8080/trains/" + Integer.parseInt(trainId);
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return Integer.parseInt(trainId);
        } else {
            return 0;
        }
    }
}
