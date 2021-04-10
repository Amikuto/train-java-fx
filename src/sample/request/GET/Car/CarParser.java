package sample.request.GET.Car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.Car;
import sample.model.Seat;
import sample.model.Train;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CarParser {
    private String json;
    private final CarGet carGet = new CarGet();

    private ArrayList<Car> getCars(JSONArray obj){
        ArrayList<Car> carArrayList = new ArrayList<>();
        for (int i=0;i<obj.length();i++) {
            JSONObject trainToParse = obj.getJSONObject(i);

            parseFunc(carArrayList, trainToParse);
        }

        return carArrayList;
    }

    private void parseFunc(ArrayList<Car> carArrayList, JSONObject trainToParse) {
        Long id = Long.parseLong(trainToParse.get("id").toString());
        String carClass = trainToParse.getString("cclass");
        String type = trainToParse.getString("ctype");
        Integer trainId = trainToParse.getInt("tid");
        ObservableList<Seat> seats = FXCollections.observableArrayList();
        JSONArray array = trainToParse.getJSONArray("seats");

        for (int j=0;j<array.length();j++){
            JSONObject seat = array.getJSONObject(j);
            Long seatId = Long.parseLong(seat.get("id").toString());
            String seatType = seat.getString("type");
            Integer seatCost = Integer.parseInt(seat.get("cost").toString());
            Long seatCarId = Long.parseLong(seat.get("id").toString());
            Seat seat_temp = new Seat(seatId, seatType, seatCost, seatCarId);
            seats.add(seat_temp);
        }

        Car car = new Car(id, carClass, type, trainId, seats);
        carArrayList.add(car);
    }

    public ArrayList<Car> getListOfCars(Long trainId) throws IOException {
        json = carGet.carGetByTrainId(trainId);
        JSONArray obj = new JSONArray(json);

        return getCars(obj);
    }
}
