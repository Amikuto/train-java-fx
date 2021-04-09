package sample.request.GET.Train;

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

public class TrainPut {

    public static void editTrain(String id, LocalDate dateDep, LocalDate dateArr, LocalTime timeDep, LocalTime timeArr, String arrSt, String depSt) throws IOException {
        final String url = "http://localhost:8080/trains/" + id;
        final HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        JSONObject json = new JSONObject();
        json.put("dateDep", dateDep);
        json.put("dateArr", dateArr);
        json.put("timeDep", timeDep);
        json.put("timeArr", timeArr);
        json.put("arrSt", arrSt);
        json.put("depSt", depSt);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("PUT");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));

//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
    }
}
