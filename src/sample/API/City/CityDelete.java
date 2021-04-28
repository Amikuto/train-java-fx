package sample.API.City;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CityDelete {

    public static Long deleteCity(Long cityId) throws IOException {
        String url = "http://localhost:8080/cities/" + cityId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return cityId;
        } else {
            return 0L;
        }
    }
}
