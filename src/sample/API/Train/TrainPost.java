package sample.API.Train;

import org.json.JSONObject;
import sample.model.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

public class TrainPost {

    public static boolean addNewTrain(Train train) throws IOException {
        final String url = "http://localhost:8080/trains";
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("dateDep", train.getDateDep());
        json.put("dateArr", train.getDateArr());
        json.put("timeDep", train.getTimeDep());
        json.put("timeArr", train.getTimeArr());
        json.put("depSt", train.getDepSt());
        json.put("arrSt", train.getArrSt());
        json.put("arrivalCity", train.getArrivalCity());
        json.put("departingCity", train.getDepartingCity());
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        return httpClient.getResponseCode() == 200;
    }
}
