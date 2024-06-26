package sample.API.Train;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Класс API поездов для парсинга информации получаемой с сервера
 * @author damir
 */
public class TrainParser {

    private String json;
    private final TrainGet trainGet = new TrainGet();

    private void parseFunc(ArrayList<Train> trainArrayList, JSONObject obj) {
        Long id = Long.parseLong(obj.get("id").toString());

        LocalTime timeDep = LocalTime.parse(obj.getString("timeDep"));
        LocalTime timeArr = LocalTime.parse(obj.getString("timeArr"));

        LocalDate dateDep = LocalDate.parse(obj.getString("dateDep"));
        LocalDate dateArr = LocalDate.parse(obj.getString("dateArr"));

        String depSt = obj.get("depSt").toString();
        String arrSt = obj.get("arrSt").toString();

        String departingCity = obj.get("departingCity").toString();
        String arrivalCity = obj.get("arrivalCity").toString();

        Train train = new Train(id, departingCity, arrivalCity, depSt, arrSt, timeDep, timeArr, dateDep, dateArr);

        trainArrayList.add(train);
    }

    private ArrayList<Train> getTrains(JSONArray obj) {
        ArrayList<Train> trainArrayList = new ArrayList<>();
        for (int i=0;i<obj.length();i++) {
            JSONObject trainToParse = obj.getJSONObject(i);

            parseFunc(trainArrayList, trainToParse);
        }

        return trainArrayList;
    }

    public ArrayList<Train> getTrainById(Long id) throws IOException {
        json = trainGet.trainGetById(id);
        ArrayList<Train> trainArrayList = new ArrayList<>();
        JSONObject obj = new JSONObject(json);

        parseFunc(trainArrayList, obj);

        return trainArrayList;
    }

    public ArrayList<Train> getAllTrains() throws IOException {

        json = trainGet.trainGetAll();

        JSONObject obj = new JSONObject(json);

        JSONArray content = obj.getJSONArray("content");

        return getTrains(content);
    }

    public ArrayList<Train> getListOfTrains(String depCity, String arrCity, String depDate) throws IOException {

        json = trainGet.trainGetByDepAndArrStationAndDepDate(depCity, arrCity, depDate);
        JSONArray obj = new JSONArray(json);

        return getTrains(obj);
    }
}
