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

    public ArrayList<Station> addStationsAndIds(String string) {

        System.out.println(string);
        JSONObject obj = new JSONObject(string);

        JSONArray content = obj.getJSONArray("content");

//        JSONObject city = content.getJSONObject(0);
//
//        Long id = Long.parseLong(city.get("id").toString());
//
//        String name = city.getString("name");

//        System.out.println(id + " " + name);


        for (int i=0;i<content.length();i++) {
            JSONObject city = content.getJSONObject(i);

            String id = city.get("id").toString();
            String cityName = city.getString("city");
            String stationName = city.getString("name");

//            station.setId(id);
//            station.setCityName(cityName);
//            station.setStationName(stationName);
            Station station = new Station(id, cityName, stationName);

            this.stationArrayList.add(station);

//            ArrayList<String> buff = new ArrayList<>();
//            buff.add(cityName);
//            buff.add(stationName);
//
//            this.cities.put(id, buff);
        }

        System.out.println(stationArrayList.get(1));
        return this.stationArrayList;
    }
}
