package sample.request;

import sample.request.GET.Station.StationGet;
import sample.request.GET.Train.TrainGet;
import sample.request.GET.User.UserGet;

import java.io.IOException;

public class ReqMain {

    public static void main(String[] args) throws IOException {

//        StationGet stationGet = new StationGet();
//
//        System.out.println(stationGet.StationGetAll());
//
//        System.out.println(stationGet.StationGetAllCities());
//
//        System.out.println(TrainGet.TrainsGetById(1L));

        UserGet userGet = new UserGet();

        if (userGet.checkPassword("ami", 123)){
            System.out.println(true);
        }
    }
}
