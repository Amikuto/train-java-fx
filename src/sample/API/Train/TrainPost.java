package sample.API.Train;

import org.json.JSONObject;

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

    public static int addNewTrain(LocalDate dateDep, LocalDate dateArr, LocalTime timeDep, LocalTime timeArr, String depSt, String arrSt, String depCity, String arrCity) throws IOException {
        final String url = "http://localhost:8080/trains";
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("dateDep", dateDep);
        json.put("dateArr", dateArr);
        json.put("timeDep", timeDep);
        json.put("timeArr", timeArr);
        json.put("depSt", depSt);
        json.put("arrSt", arrSt);
        json.put("arrivalCity", arrCity);
        json.put("departingCity", depCity);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));

//        System.out.println(httpClient.getResponseCode());

        return httpClient.getResponseCode();

//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
    }
}
