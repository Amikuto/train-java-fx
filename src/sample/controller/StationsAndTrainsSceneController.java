package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Station;
import sample.model.Train;
import sample.request.GET.Station.*;
import sample.request.GET.Train.TrainDelete;
import sample.request.GET.Train.TrainParser;
import sample.request.GET.Train.TrainPost;
import sample.request.GET.Train.TrainPut;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class StationsAndTrainsSceneController {

//    private final ObservableList<Station> stationsData = FXCollections.observableArrayList();
    private final ObservableList<Train> trainsData = FXCollections.observableArrayList();
    private ObservableList<Station> stationsData;
    TrainParser trainParser = new TrainParser();
    Integer departCity = 0;
    Integer arriveCity = 0;

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
    public Button deleteButton;
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
    public TableColumn<Train, LocalDate> trainDateDepColumn;
    @FXML
    public TableColumn<Train, LocalDate> trainDateArrColumn;
    @FXML
    public Button addTrainButton;
    @FXML
    public Button editButton1;
    @FXML
    public Button trainSearchButton;
    public TextField trainCityDepNumberField;
    public TextField trainCityArrNumberField;
    public DatePicker trainDateField;
    public Button carsEditButton;

    private Main mainApp;

    public StationsAndTrainsSceneController(){}

    public void setMainApp(Main mainApp) throws IOException {
        this.mainApp = mainApp;
        this.stationsData = mainApp.getStationsData();

        showStationsData();
    }

    private void showStationsData() throws IOException {
        stationsData.clear();

        StationParser stationParser = new StationParser();
        StationGet stationGet = new StationGet();

        try {
            stationsData.addAll(stationParser.getAllStationsAndIds(stationGet.stationGetAll()));
        } catch (ConnectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Нет соединения с сервером");

            alert.showAndWait();
        }

        stationTableView.setItems(stationsData);

    }

    private void showTrainsData() throws IOException {
        trainsData.clear();

//        for (Station station : stationsData) {
//            if (trainCityDepNumberField.getText().equals(station.getStationName())){
//                this.departCity = Integer.parseInt(station.getId());
//            } else if (trainCityArrNumberField.getText().equals(station.getStationName())){
//                this.arriveCity = Integer.parseInt(station.getId());
//            }
//        }

//        String date = trainDateField.getValue().toString();
        String date = "2021-03-18"; // TODO: remove
        this.departCity = 2;
        this.arriveCity = 1;

        trainsData.addAll(trainParser.getListOfTrains(departCity, arriveCity, date));
        for (Train train : trainsData) {
            train.setDepSt(trainCityDepNumberField.getText());
            train.setArrSt(trainCityArrNumberField.getText());
        }
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

    public void addNewStation() throws IOException {
        Station station = new Station();
        boolean okClicked = mainApp.showStationEditDialog(station);
        if (okClicked) {
            StationPost.addNewStation(station.getStationName(), station.getCityName());
            showStationsData();
        }
    }

    public void editStation() throws IOException {
        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
        if (selectedStation != null) {
            boolean okClicked = mainApp.showStationEditDialog(selectedStation);
            if (okClicked) {
                StationPut.editStation(selectedStation.getId(), selectedStation.getStationName(), selectedStation.getCityName());
                showStationsData();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setContentText("Пожалуйста, выберите станцию для редактирования!");
            alert.setTitle("No selection");
            alert.setHeaderText("No station selected");

            alert.showAndWait();
        }
    }

    public void deleteStation() throws IOException {
        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
        if (selectedStation.getId().length() != 0) {
            String id = selectedStation.getId();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Удалена станция под номером " + StationDelete.deleteStation(id));

            alert.showAndWait();
            showStationsData();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Ошибка удаления");
            alert.setContentText("Выберите станцию для удаления!!!");

            alert.showAndWait();
        }
    }

    public void addNewTrain() throws IOException {
        Train train = new Train();
        boolean okClicked = mainApp.showTrainEditDialog(train);
        if (okClicked) {
            TrainPost.addNewTrain(train.getDateDep(), train.getDateArr(), train.getTimeDep(), train.getTimeArr(), train.getDepSt(), train.getArrSt());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(train.toString());

            alert.showAndWait();
        }
    }

    public void editTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            boolean okClicked = mainApp.showTrainEditDialog(selectedTrain);
            if (okClicked) {
                TrainPut.editTrain(selectedTrain.getId(), selectedTrain.getDateDep(), selectedTrain.getDateArr(), selectedTrain.getTimeDep(), selectedTrain.getTimeArr(), selectedTrain.getDepSt(), selectedTrain.getArrSt());
                showTrainsData();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setContentText("Пожалуйста, выберите поезд для редактирования!");
            alert.setTitle("No selection");
            alert.setHeaderText("No train selected");

            alert.showAndWait();
        }
    }

    public void deleteTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain.getId().length() != 0) {
            String id = selectedTrain.getId();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Удален поезд под номером " + TrainDelete.deleteTrain(id));

            alert.showAndWait();
            showTrainsData();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Ошибка удаления");
            alert.setContentText("Выберите поезд для удаления!!!");

            alert.showAndWait();
        }
    }

    public void searchTrains() throws IOException {
//        ObservableList<Train> trainsData = FXCollections.observableArrayList();

//        int departCity = 0;
//        int arriveCity = 0;
//
//        for (Station station : stationsData) {
//            if (trainCityDepNumberField.getText().equals(station.getStationName())){
//                departCity = Integer.parseInt(station.getId());
//            } else if (trainCityArrNumberField.getText().equals(station.getStationName())){
//                arriveCity = Integer.parseInt(station.getId());
//            }
//        }
//
//        String date = trainDateField.getValue().toString();
////        String date = "2021-03-18";
//
//        trainsData.addAll(trainParser.getListOfTrains(departCity, arriveCity, date));
//        for (Train train : trainsData) {
//            train.setDepSt(trainCityDepNumberField.getText());
//            train.setArrSt(trainCityArrNumberField.getText());
//        }

        showTrainsData();
        trainTableView.setItems(trainsData);
        trainIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        trainCityDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().depStProperty());
        trainCityArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().arrStProperty());
        trainTimeDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().timeDepProperty());
        trainTimeArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().timeArrProperty());
        trainDateDepColumn.setCellValueFactory(cellData -> cellData.getValue().dateDepProperty());
        trainDateArrColumn.setCellValueFactory(cellData -> cellData.getValue().dateArrProperty());
    }

    public void showCarsAndSeatsScene() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            mainApp.showCarsAndSeatsScene(selectedTrain);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setContentText("Пожалуйста, выберите поезд для редактирования!");
            alert.setTitle("No selection");
            alert.setHeaderText("No train selected");

            alert.showAndWait();
        }
//        mainApp.showCarsAndSeatsScene();
    }
}
