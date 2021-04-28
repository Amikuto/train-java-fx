package sample.API.Station;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.Station;

import java.io.IOException;
import java.util.ArrayList;

public class StationParser {

    private String json;
    private final StationGet stationGet = new StationGet();
    public ArrayList<Station> stationArrayList = new ArrayList<>();
//    public HashMap<String, List<String>> cities = new HashMap<>();

    public void parseFunc(ArrayList<Station> stationArrayList, JSONObject obj) {

            Long id = Long.parseLong(obj.get("id").toString());
            String cityName = obj.getString("cityName");
            String stationName = obj.getString("name");

            Station station = new Station(id, cityName, stationName);

            stationArrayList.add(station);
    }

    private ArrayList<Station> getStations(JSONArray obj) {
        ArrayList<Station> stationArrayList = new ArrayList<>();
        for (int i=0;i<obj.length();i++) {
            JSONObject stationToParse = obj.getJSONObject(i);

            parseFunc(stationArrayList, stationToParse);
        }

        return stationArrayList;
    }

    public ArrayList<Station> getAllStationsByCityName(String cityName) throws IOException {
        json = stationGet.getAllStationsByCityName(cityName);
        JSONArray obj = new JSONArray(json);
        return getStations(obj);
    }
}
