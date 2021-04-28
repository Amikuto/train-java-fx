package sample.API.Seat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeatDelete {

    public static Long deleteSeat(Long seatId) throws IOException {
        String url = "http://localhost:8080/seats/" + seatId;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("DELETE");
        if (httpClient.getResponseCode() == 200) {
            return seatId;
        } else {
            return 0L;
        }
    }
}
