package sample.request.GET.Train;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.Train;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TrainParser {

    private String json;

    private final TrainGet trainGet = new TrainGet();

    private void parseFunc(ArrayList<Train> trainArrayList, JSONObject obj) {
        String id = obj.get("id").toString();

        LocalTime timeDep = LocalTime.parse(obj.getString("timeDep"));

        LocalTime timeArr = LocalTime.parse(obj.getString("timeArr"));

        LocalDate dateDep = LocalDate.parse(obj.getString("dateDep"));

        LocalDate dateArr = LocalDate.parse(obj.getString("dateArr"));
        String arrSt = obj.get("arrSt").toString();
        String depSt = obj.get("depSt").toString();

        Train train = new Train(id, depSt, arrSt, timeDep, timeArr, dateDep, dateArr);

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

    public ArrayList<Train> getListOfTrains(Integer depStationId, Integer arrStationId, String depDate) throws IOException {

        json = trainGet.trainGetByDepAndArrStationAndDepDate(depStationId, arrStationId, depDate);

        JSONArray obj = new JSONArray(json);

        return getTrains(obj);
    }
}
