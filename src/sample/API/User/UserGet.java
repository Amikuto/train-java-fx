package sample.API.User;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserGet {

    public static boolean checkPassword(String login, Integer password) throws IOException {
        String url = "http://localhost:8080/users/password/" + login;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
//        httpClient.setDoOutput(true);
//        httpClient.setRequestMethod("POST");

//        OutputStream os = httpClient.getOutputStream();
//        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//        osw.write("""
//                {
//                    'password': 123
//                }""");
//        System.out.println(osw);
//        osw.flush();
//        osw.close();
//        os.close();  //don't forget to close the OutputStream
//        httpClient.connect();

        JSONObject json = new JSONObject();
        json.put("password", password);
        byte[] postDataBytes = json.toString().getBytes(StandardCharsets.UTF_8);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpClient.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        httpClient.setDoOutput(true);
        httpClient.getOutputStream().write(postDataBytes);

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine().equals("true");
    }
}
