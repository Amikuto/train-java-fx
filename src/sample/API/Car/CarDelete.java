package sample.API.Car;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarDelete {

    public static Long deleteCar(Long carId) throws IOException {
        String url = "http://localhost:8080/cars/" + carId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return carId;
        } else {
            return 0L;
        }
    }
}
