package sample.request;

import sample.model.City;
import sample.model.Seat;
import sample.model.Train;
import sample.request.GET.Car.CarDelete;
import sample.request.GET.Car.CarParser;
import sample.request.GET.Car.CarPost;
import sample.request.GET.Car.CarPut;
import sample.request.GET.City.CityDelete;
import sample.request.GET.City.CityParser;
import sample.request.GET.City.CityPost;
import sample.request.GET.City.CityPut;
import sample.request.GET.Seat.SeatDelete;
import sample.request.GET.Seat.SeatPost;
import sample.request.GET.Seat.SeatPut;
import sample.request.GET.Station.*;
import sample.request.GET.Train.TrainDelete;
import sample.request.GET.Train.TrainParser;
import sample.request.GET.Train.TrainPost;
import sample.request.GET.Train.TrainPut;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReqMain {

    public static void main(String[] args) throws IOException {

//        StationGet stationGet = new StationGet();
        StationParser stationParser = new StationParser();

//        System.out.println(stationParser.getAllStationsAndIds(stationGet.stationGetAll()));
//        System.out.println(stationParser.getAllStationsByCityName("Казань"));

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
//        System.out.println(trainParser.getListOfTrains("Москва", "Казань", "2021-03-18"));
//        StationPost.addNewStation("ьгкв", "Пермь");
//        System.out.println(StationDelete.deleteStation(113));
//        StationPut.editStation("3", "Test", "TestCity");

//        TrainPost.addNewTrain(LocalDate.parse("2021-03-18"), LocalDate.parse("2021-03-19"), LocalTime.parse("09:00:00"), LocalTime.parse("16:00:00"), "1", "2");
//        TrainDelete.deleteTrain(3L);
//        TrainPut.editTrain("316", LocalDate.parse("2021-03-18"), LocalDate.parse("2021-03-19"), LocalTime.parse("01:00:00"), LocalTime.parse("10:00:00"), "1", "2");

//        CarParser carParser = new CarParser();
//        System.out.println(carParser.getListOfCars(12L));

//        CityParser cityParser = new CityParser();
//        ArrayList<City> cityArrayList = cityParser.getAllCities();
//        for (int i=0; i<cityArrayList.size(); i++)
//            System.out.println(cityArrayList.get(i).getName());
//        System.out.println(cityParser.getAllCities().size());
//        CityPost.addNewCity("testName");
//        CityPut.editCity(62L, "TestName2");
//        CityDelete.deleteCity(62L);

//        System.out.println(CarDelete.deleteCar(10L));
//        CarPost.addNewCar(3, "СВ", 1, "1");
//        CarPut.editCar(11L, 4, "СВ", 1, "2");

//        System.out.println(SeatDelete.deleteSeat(1L));
        SeatPost.addNewSeat(123, 123, "upper", 1);
//        SeatPut.editSeat(50L, 2345, 888, "lower", 1);
    }
}
