package sample.request;

import sample.model.Train;
import sample.request.GET.Car.CarParser;
import sample.request.GET.Station.*;
import sample.request.GET.Train.TrainDelete;
import sample.request.GET.Train.TrainPost;
import sample.request.GET.Train.TrainPut;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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

//        TrainParser trainParser = new TrainParser();
//        System.out.println(trainParser.getAllTrains());
//        System.out.println(trainParser.getTrainById(12L));
//        System.out.println(trainParser.getListOfTrains(2, 1));
//        System.out.println(trainParser.getListOfTrains(2, 1, "2021-03-18"));
//        StationPost.addNewStation("Test", "TestCity");
//        System.out.println(StationDelete.deleteStation(113));
//        StationPut.editStation("3", "Test", "TestCity");
//        TrainPost.addNewTrain(LocalDate.parse("2021-03-18"), LocalDate.parse("2021-03-19"), LocalTime.parse("09:00:00"), LocalTime.parse("16:00:00"), "1", "2");
//        TrainDelete.deleteTrain("312");
//        TrainPut.editTrain("316", LocalDate.parse("2021-03-18"), LocalDate.parse("2021-03-19"), LocalTime.parse("01:00:00"), LocalTime.parse("10:00:00"), "1", "2");
        CarParser carParser = new CarParser();
        System.out.println(carParser.getListOfCars(12L));
    }
}
