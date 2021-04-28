package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.City;
import sample.model.Station;
import sample.model.Train;
import sample.request.GET.City.CityDelete;
import sample.request.GET.City.CityParser;
import sample.request.GET.City.CityPost;
import sample.request.GET.Station.*;
import sample.request.GET.Train.TrainDelete;
import sample.request.GET.Train.TrainParser;
import sample.request.GET.Train.TrainPost;
import sample.request.GET.Train.TrainPut;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class StationsAndTrainsSceneController {

//    private final ObservableList<Station> stationsData = FXCollections.observableArrayList();
//    private ObservableList<Station> stationsData;
//    private ObservableList<Station> citiesData = FXCollections.observableArrayList();
    private final ObservableList<City> cityData = FXCollections.observableArrayList();
    private final ObservableList<Train> trainsData = FXCollections.observableArrayList();
    TrainParser trainParser = new TrainParser();
    CityParser cityParser = new CityParser();
//    Integer departCity = 0;
//    Integer arriveCity = 0;

    public TableView<City> cityTableView;
    @FXML
    public TableColumn<City, Long> idColumn;
    @FXML
    public TableColumn<City, String> cityNameColumn;
    @FXML
    public TableColumn<Station, String> stationNameColumn;
    @FXML
    public TableView<Train> trainTableView;
    @FXML
    public TableColumn<Train, Long> trainIdColumn;
    public TableColumn<Train, String> trainCityDepartingColumn;
    public TableColumn<Train, String> trainCityArrivingColumn;
    @FXML
    public TableColumn<Train, String> trainStationDepartingColumn;
    @FXML
    public TableColumn<Train, String> trainStationArrivingColumn;
    @FXML
    public TableColumn<Train, LocalTime> trainTimeDepartingColumn;
    @FXML
    public TableColumn<Train, LocalTime> trainTimeArrivingColumn;
    @FXML
    public TableColumn<Train, LocalDate> trainDateDepColumn;
    @FXML
    public TableColumn<Train, LocalDate> trainDateArrColumn;
//    @FXML
//    public Button addTrainButton;
//    @FXML
//    public Button editButton1;
//    @FXML
    public Button trainSearchButton;
    public TextField trainCityDepNumberField;
    public TextField trainCityArrNumberField;
    public DatePicker trainDateField;
    public Button carsEditButton;
    public Label cityIdLabel;
    public Label cityNameLabel;
    public Button addStationButton;
    public Button editStationButton;
    public Button deleteStationButton;
    public ListView<Station> stationsListView;
    public Button addCityButton;
    public Button deleteCityButton;
    public TextField cityTextField;
    public TextField stationNameField;

    private Main mainApp;

    public StationsAndTrainsSceneController(){}

    public void setMainApp(Main mainApp) throws IOException {
        this.mainApp = mainApp;

        refreshData();
    }

    public static void showWarningPopup(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void refreshData() {

        try {
            cityData.clear();
            cityData.addAll(cityParser.getAllCities());
            cityTableView.setItems(cityData);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setContentText("Нету связи с сервером!");
            alert.setTitle("No connection");
            alert.setHeaderText("No server connection");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                refreshData();
            } else {
                alert.close();
            }
            alert.showAndWait();
        }
    }

    public void showCitiesData(City city) {
        if (city != null) {
            stationsListView.getItems().clear();
            cityIdLabel.setText(city.getId().toString());
            cityNameLabel.setText(city.getName());
            stationsListView.getItems().addAll(city.getStations());
        } else {
            cityIdLabel.setText("");
            cityNameLabel.setText("");
        }
    }

//    private void showStationsData() throws IOException {
//        stationsData.clear();
//
//        StationParser stationParser = new StationParser();
//        StationGet stationGet = new StationGet();
//
//        CityParser cityParser = new CityParser();
//
//        try {
////            stationsData.addAll(cityParser.getAllCities());
//            stationsData.addAll(stationParser.getAllStationsAndIds(stationGet.stationGetAll()));
//        } catch (ConnectException e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Нет соединения с сервером");
//
//            alert.showAndWait();
//        }
//
//        stationTableView.setItems(stationsData);
//
//    }

//    private void showTrainsData() throws IOException {
//        trainsData.clear();
//
////        for (Station station : stationsData) {
////            if (trainCityDepNumberField.getText().equals(station.getStationName())){
////                this.departCity = Integer.parseInt(station.getId());
////            } else if (trainCityArrNumberField.getText().equals(station.getStationName())){
////                this.arriveCity = Integer.parseInt(station.getId());
////            }
////        }
//
////        String date = trainDateField.getValue().toString();
//        String date = "2021-03-18"; // TODO: remove
//        this.departCity = 2;
//        this.arriveCity = 1;
//
//        trainsData.addAll(trainParser.getListOfTrains(departCity, arriveCity, date));
//        for (Train train : trainsData) {
//            train.setDepSt(trainCityDepNumberField.getText());
//            train.setArrSt(trainCityArrNumberField.getText());
//        }
//    }

    @FXML
    private void initialize(){
        cityTextField.setText("Введите название города для добавления");
        stationsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        trainDateField.setPromptText("dd-MM-yyyy");

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cityNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showCitiesData(null);
        cityTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCitiesData(newValue)
        );
    }

//    public void addNewStation() throws IOException {
//        Station station = new Station();
//        boolean okClicked = mainApp.showStationEditDialog(station);
//        if (okClicked) {
//            StationPost.addNewStation(station.getStationName(), station.getCityName());
//            showStationsData();
//        }
//    }

//    public void editStation() throws IOException {
//        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
//        if (selectedStation != null) {
//            boolean okClicked = mainApp.showStationEditDialog(selectedStation);
//            if (okClicked) {
//                StationPut.editStation(selectedStation.getId(), selectedStation.getStationName(), selectedStation.getCityName());
//                showStationsData();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setContentText("Пожалуйста, выберите станцию для редактирования!");
//            alert.setTitle("No selection");
//            alert.setHeaderText("No station selected");
//
//            alert.showAndWait();
//        }
//    }

    public void addNewCity() throws IOException {
        String cityName = cityTextField.getText();
        if (cityName.length() != 0){
            if (!cityName.equals("Введите название города для добавления")) {
                CityPost.addNewCity(cityName);
            } else {
                showWarningPopup("No input", "No city name printed", "Пожалуйста, введите правильное название города!");
            }
        } else {
            showWarningPopup("No input", "No city name printed", "Пожалуйста, введите название города!");
        }
        refreshData();
    }

    public void deleteCity() throws IOException {
        City selectedCity = cityTableView.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            CityDelete.deleteCity(selectedCity.getId());
            refreshData();
        } else {
            showWarningPopup("No selection", "No city selected", "Пожалуйста, выберите город для удаления!");
        }
    }

    public void addNewStation() throws IOException {
        City selectedCity = cityTableView.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            if (stationNameField.getText().length() != 0) {
                StationPost.addNewStation(stationNameField.getText(), selectedCity.getName());
                refreshData();
            } else {
                showWarningPopup("No input", "No city selected", "Пожалуйста, введите название новой станции!");
            }
        } else {
            showWarningPopup("No selection", "No city selected", "Пожалуйста, выберите город для добавления новой станции!");
        }
    }

    public void editStation() throws IOException {
        Station station = stationsListView.getSelectionModel().getSelectedItem();
        if (station != null) {
            StationPut.editStation(station.getId(), stationNameField.getText());
        } else {
            showWarningPopup("No selection", "No station selected", "Пожалуйста, выберите станцию для изменения!");
        }
        refreshData();
    }

    public void deleteStation() throws IOException {
        ObservableList<Station> list = stationsListView.getSelectionModel().getSelectedItems();
        if (list.size() != 0) {
            for (Station i:list) {
                StationDelete.deleteStation(i.getId());
            }
        } else {
            showWarningPopup("No selection", "No station selected", "Пожалуйста, выберите станции для удаления!");
        }
        refreshData();
    }

    public void addNewTrain() throws IOException {
        Train train = new Train();
        boolean okClicked = mainApp.showTrainEditDialog(train);
        if (okClicked) {
            TrainPost.addNewTrain(
                    train.getDateDep(), train.getDateArr(),
                    train.getTimeDep(), train.getTimeArr(),
                    train.getDepSt(), train.getArrSt(),
                    train.getDepartingCity(), train.getArrivalCity()
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(train + " добавлен!\n\n\nОбновите данные на странице!");

            alert.showAndWait();
        }
    }

    public void editTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            boolean okClicked = mainApp.showTrainEditDialog(selectedTrain);
            if (okClicked) {
                TrainPut.editTrain(
                        selectedTrain.getId(),
                        selectedTrain.getDateDep(), selectedTrain.getDateArr(),
                        selectedTrain.getTimeDep(), selectedTrain.getTimeArr(),
                        selectedTrain.getDepSt(), selectedTrain.getArrSt(),
                        selectedTrain.getDepartingCity(), selectedTrain.getArrivalCity()
                );
            }
        } else {
            showWarningPopup("No selection", "No train selected", "Пожалуйста, выберите поезд для редактирования!");
        }
    }

    public void deleteTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            TrainDelete.deleteTrain(selectedTrain.getId());
            refreshData();
        } else {
            showWarningPopup("No selection", "No train selected", "Пожалуйста, выберите поезд для удаления!");
        }
    }

    public void searchTrains() throws IOException {
        trainsData.clear();

//        String depCity = trainCityDepNumberField.getText();
//        String arrCity = trainCityArrNumberField.getText();
//        String depDate = trainDateField.getValue().toString();
        String depCity = "Москва";
        String arrCity = "Казань";
        String depDate = "2021-03-18";

        trainsData.addAll(trainParser.getListOfTrains(depCity, arrCity, depDate));
        trainTableView.setItems(trainsData);

        trainIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        trainCityDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().departingCityProperty());
        trainCityArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalCityProperty());

        trainStationDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().depStProperty());
        trainStationArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().arrStProperty());

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
            showWarningPopup("No train selected", "No selection", "Пожалуйста, выберите поезд для редактирования!");
        }
    }

////    public void addNewStation() throws IOException {
////        Station station = new Station();
////        boolean okClicked = mainApp.showStationEditDialog(station);
////        if (okClicked) {
////            StationPost.addNewStation(station.getStationName(), station.getCityName());
////            showStationsData();
////        }
////    }
////
////    public void editStation() throws IOException {
////        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
////        if (selectedStation != null) {
////            boolean okClicked = mainApp.showStationEditDialog(selectedStation);
////            if (okClicked) {
////                StationPut.editStation(selectedStation.getId(), selectedStation.getStationName(), selectedStation.getCityName());
////                showStationsData();
////            }
////        } else {
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.initOwner(mainApp.getPrimaryStage());
////            alert.setContentText("Пожалуйста, выберите станцию для редактирования!");
////            alert.setTitle("No selection");
////            alert.setHeaderText("No station selected");
////
////            alert.showAndWait();
////        }
////    }
////
////    public void deleteStation() throws IOException {
////        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
////        if (selectedStation.getId().length() != 0) {
////            String id = selectedStation.getId();
////            Alert alert = new Alert(Alert.AlertType.INFORMATION);
////            alert.setTitle("Information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("Удалена станция под номером " + StationDelete.deleteStation(id));
////
////            alert.showAndWait();
////            showStationsData();
////        } else {
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.initOwner(mainApp.getPrimaryStage());
////            alert.setTitle("Ошибка!");
////            alert.setHeaderText("Ошибка удаления");
////            alert.setContentText("Выберите станцию для удаления!!!");
////
////            alert.showAndWait();
////        }
////    }
//
////    public void addNewTrain() throws IOException {
////        Train train = new Train();
////        boolean okClicked = mainApp.showTrainEditDialog(train);
////        if (okClicked) {
////            TrainPost.addNewTrain(train.getDateDep(), train.getDateArr(), train.getTimeDep(), train.getTimeArr(), train.getDepSt(), train.getArrSt());
////            Alert alert = new Alert(Alert.AlertType.INFORMATION);
////            alert.setTitle("Information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText(train.toString());
////
////            alert.showAndWait();
////        }
////    }
////
////    public void editTrain() throws IOException {
////        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
////        if (selectedTrain != null) {
////            boolean okClicked = mainApp.showTrainEditDialog(selectedTrain);
////            if (okClicked) {
////                TrainPut.editTrain(selectedTrain.getId(), selectedTrain.getDateDep(), selectedTrain.getDateArr(), selectedTrain.getTimeDep(), selectedTrain.getTimeArr(), selectedTrain.getDepSt(), selectedTrain.getArrSt());
////                showTrainsData();
////            }
////        } else {
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.initOwner(mainApp.getPrimaryStage());
////            alert.setContentText("Пожалуйста, выберите поезд для редактирования!");
////            alert.setTitle("No selection");
////            alert.setHeaderText("No train selected");
////
////            alert.showAndWait();
////        }
////    }
////
////    public void deleteTrain() throws IOException {
////        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
////        if (selectedTrain.getId().length() != 0) {
////            String id = selectedTrain.getId();
////            Alert alert = new Alert(Alert.AlertType.INFORMATION);
////            alert.setTitle("Information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("Удален поезд под номером " + TrainDelete.deleteTrain(id));
////
////            alert.showAndWait();
////            showTrainsData();
////        } else {
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.initOwner(mainApp.getPrimaryStage());
////            alert.setTitle("Ошибка!");
////            alert.setHeaderText("Ошибка удаления");
////            alert.setContentText("Выберите поезд для удаления!!!");
////
////            alert.showAndWait();
////        }
////    }
//
//    public void searchTrains() throws IOException {
//        ObservableList<Train> trainsData = FXCollections.observableArrayList();
//
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
//
////        showTrainsData();
//        trainTableView.setItems(trainsData);
//        trainIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
//        trainCityDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().depStProperty());
//        trainCityArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().arrStProperty());
//        trainTimeDepartingColumn.setCellValueFactory(cellData -> cellData.getValue().timeDepProperty());
//        trainTimeArrivingColumn.setCellValueFactory(cellData -> cellData.getValue().timeArrProperty());
//        trainDateDepColumn.setCellValueFactory(cellData -> cellData.getValue().dateDepProperty());
//        trainDateArrColumn.setCellValueFactory(cellData -> cellData.getValue().dateArrProperty());
//    }
//
//    public void showCarsAndSeatsScene() throws IOException {
//        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
//        if (selectedTrain != null) {
//            mainApp.showCarsAndSeatsScene(selectedTrain);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setContentText("Пожалуйста, выберите поезд для редактирования!");
//            alert.setTitle("No selection");
//            alert.setHeaderText("No train selected");
//
//            alert.showAndWait();
//        }
////        mainApp.showCarsAndSeatsScene();
//    }
}
