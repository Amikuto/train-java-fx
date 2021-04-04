package sample.request;

import sample.request.GET.Station.StationGet;
import sample.request.GET.Station.StationParser;
import sample.request.GET.Train.TrainGet;
import sample.request.GET.Train.TrainParser;

import java.io.IOException;

public class ReqMain {

    public static void main(String[] args) throws IOException {

        StationGet stationGet = new StationGet();
        StationParser stationParser = new StationParser();

//        System.out.println(stationParser.getAllStationsAndIds(stationGet.stationGetAll()));

//        System.out.println(stationGet.StationGetAll());
//
//        System.out.println(stationGet.StationGetAllCities());

//        System.out.println(TrainGet.trainGetAll());
//        UserGet userGet = new UserGet();

//        if (userGet.checkPassword("ami", 123)){
//            System.out.println(true);
//        }

        TrainParser trainParser = new TrainParser();
//
        System.out.println(trainParser.getAllTrains());

        System.out.println(trainParser.getTrainById(12L));

//        System.out.println(trainParser.getListOfTrains(2, 1));

        System.out.println(trainParser.getListOfTrains(2, 1, "2021-03-18"));
    }
}
