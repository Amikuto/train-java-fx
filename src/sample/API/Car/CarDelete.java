package sample.API.Car;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarDelete {

    public static boolean deleteCar(Long carId) throws IOException {
        String url = "http://localhost:8080/cars/" + carId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        return httpClient.getResponseCode() == 200;
    }
}
