package sample.request.GET.Station;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationParser {

    private StationGet stationGet;
    public ArrayList<Station> stationArrayList = new ArrayList<>();
    public HashMap<String, List<String>> cities = new HashMap<>();

    public ArrayList<Station> getAllStationsAndIds(String string) {

        JSONObject obj = new JSONObject(string);

        JSONArray content = obj.getJSONArray("content");

        for (int i=0;i<content.length();i++) {
            JSONObject stationToParse = content.getJSONObject(i);

            Long id = Long.parseLong(stationToParse.get("id").toString());
            String cityName = stationToParse.getString("city");
            String stationName = stationToParse.getString("name");

            Station station = new Station(id, cityName, stationName);

            this.stationArrayList.add(station);
        }

        return this.stationArrayList;
    }
}
