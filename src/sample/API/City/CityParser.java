package sample.API.City;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.City;
import sample.model.Station;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс API городов для парсинга информации получаемой с сервера
 * @author damir
 */
public class CityParser {

    private String json;
    private final CityGet cityGet = new CityGet();

    private void parseFunc(ArrayList<City> cityArrayList, JSONObject cityToParse) {
        Long id = Long.parseLong(cityToParse.get("id").toString());
        String name = cityToParse.getString("name");

        ObservableList<Station> stations = FXCollections.observableArrayList();
        JSONArray array = cityToParse.getJSONArray("stations");
        for (int j=0;j<array.length();j++){
            JSONObject station = array.getJSONObject(j);
            Long stationId = Long.parseLong(station.get("id").toString());
            String stationName = station.getString("name");

            Station stationTemp = new Station(stationId, stationName);
            stations.add(stationTemp);
        }

        City city = new City(id, name, stations);
        cityArrayList.add(city);
    }

    private ArrayList<City> getCities(JSONArray objects){
        ArrayList<City> cityArrayList = new ArrayList<>();
        for (int i=0;i<objects.length();i++) {
            JSONObject cityToParse = objects.getJSONObject(i);

            parseFunc(cityArrayList, cityToParse);
        }

        return cityArrayList;
    }

    public ArrayList<City> getAllCities() throws IOException {
        json = cityGet.citiesGetAll();
        JSONObject object = new JSONObject(json);
        JSONArray content = object.getJSONArray("content");
        return getCities(content);
    }
}
