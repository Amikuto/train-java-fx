package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Station;
import sample.model.Train;
import sample.request.GET.Station.StationGet;
import sample.request.GET.Station.StationParser;
import sample.request.GET.Train.TrainParser;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

public class StationsAndTrainsSceneController {

    private final ObservableList<Station> stationsData = FXCollections.observableArrayList();
    public TextField trainCityDepNumberField;
    public TextField trainCityArrNumberField;
    public DatePicker trainDateField;
    TrainParser trainParser = new TrainParser();

    @FXML
    public TableView<Station> stationTableView;
    @FXML
    public TableColumn<Station, String> idColumn;
    @FXML
    public TableColumn<Station, String> cityNameColumn;
    @FXML
    public TableColumn<Station, String> stationNameColumn;
    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public TableView<Train> trainTableView;
    @FXML
    public TableColumn<Train, String> trainIdColumn;
    @FXML
    public TableColumn<Train, String> trainCityDepartingColumn;
    @FXML
    public TableColumn<Train, String> trainCityArrivingColumn;
    @FXML
    public TableColumn<Train, LocalTime> trainTimeDepartingColumn;
    @FXML
    public TableColumn<Train, LocalTime> trainTimeArrivingColumn;
    @FXML
    public Button addButton1;
    @FXML
    public Button editButton1;
    @FXML
    public Button trainSearchButton;

    private Main mainApp;

    public StationsAndTrainsSceneController(){}

    public void setMainApp(Main mainApp) throws IOException {
        this.mainApp = mainApp;

        StationParser stationParser = new StationParser();
        StationGet stationGet = new StationGet();

        stationsData.addAll(stationParser.getAllStationsAndIds(stationGet.stationGetAll()));

        stationTableView.setItems(stationsData);
    }

    @FXML
    private void initialize(){
//        trainDateField.setPromptText("dd-MM-yyyy");

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        cityNameColumn.setCellValueFactory(cellData -> cellData.getValue().cityNameProperty());
        stationNameColumn.setCellValueFactory(cellData -> cellData.getValue().stationNameProperty());

//        stationTableView.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> stationTableView(newValue)
//        );
    }

    public void addNewStation(ActionEvent actionEvent) {
    }

    public void editStation(ActionEvent actionEvent) {
    }

    public void addNewTrain(ActionEvent actionEvent) {
    }

    public void editTrain(ActionEvent actionEvent) {
    }

    public void searchTrains() throws IOException {
        ObservableList<Train> trainsData = FXCollections.observableArrayList();

        int departCity = 0;
        int arriveCity = 0;

        System.out.println(stationsData);

        for (Station station : stationsData) {
            if (trainCityDepNumberField.getText().equals(station.getStationName())){
                departCity = Integer.parseInt(station.getId());
            } else if (trainCityArrNumberField.getText().equals(station.getStationName())){
                arriveCity = Integer.parseInt(station.getId());
            }
        }

//        String date = trainDateField.getValue().toString();
        String date = "2021-03-18"; // TODO: remove

        trainsData.addAll(trainParser.getListOfTrains(departCity, arriveCity, date));
        for (Train train : trainsData) {
            train.setDepSt(trainCityDepNumberField.getText());
            train.setArrSt(trainCityArrNumberField.getText());
        }
        trainTableView.setItems(trainsData);
        trainIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        trainCityDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().depStProperty());
        trainCityArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().arrStProperty());
        trainTimeDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().timeDepProperty());
        trainTimeArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().timeArrProperty());

    }
}
