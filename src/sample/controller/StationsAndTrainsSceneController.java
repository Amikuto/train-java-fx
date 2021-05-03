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
import sample.API.City.CityDelete;
import sample.API.City.CityParser;
import sample.API.City.CityPost;
import sample.API.Station.*;
import sample.API.Train.TrainDelete;
import sample.API.Train.TrainParser;
import sample.API.Train.TrainPost;
import sample.API.Train.TrainPut;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class StationsAndTrainsSceneController {

    private final ObservableList<City> cityData = FXCollections.observableArrayList();
    private final ObservableList<Train> trainsData = FXCollections.observableArrayList();
    TrainParser trainParser = new TrainParser();
    CityParser cityParser = new CityParser();

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
    public TextField trainCityDepNumberField;
    public TextField trainCityArrNumberField;
    public DatePicker trainDateField;
    public Label cityIdLabel;
    public Label cityNameLabel;
    public ListView<Station> stationsListView;
    public TextField cityTextField;
    public TextField stationNameField;

    private Main mainApp;

    public StationsAndTrainsSceneController(){}

    public void setMainApp(Main mainApp) {
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

    public static void showInfoPopup(String text, String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text + " " + type + "!\n\n\nОбновите данные на странице!");

        alert.showAndWait();
    }

    public void refreshData() {
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

    public void addNewCity() throws IOException {
        String cityName = cityTextField.getText();
        if (cityName.length() != 0){
            if (!cityName.equals("Введите название города для добавления")) {
                if (CityPost.addNewCity(cityName)) {
                    showInfoPopup("Город", "добавлен");
                    refreshData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            } else {
                showWarningPopup("No input", "No city name printed", "Пожалуйста, введите правильное название города!");
            }
        } else {
            showWarningPopup("No input", "No city name printed", "Пожалуйста, введите название города!");
        }
    }

    public void deleteCity() throws IOException {
        City selectedCity = cityTableView.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            if (CityDelete.deleteCity(selectedCity.getId())) {
                showInfoPopup("Город", "удален");
                refreshData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        } else {
            showWarningPopup("No selection", "No city selected", "Пожалуйста, выберите город для удаления!");
        }
    }

    public void addNewStation() throws IOException {
        City selectedCity = cityTableView.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            if (stationNameField.getText().length() != 0) {
                if (StationPost.addNewStation(stationNameField.getText(), selectedCity.getName())) {
                    showInfoPopup("Станция", "добавлена");
                    refreshData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
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
            if (StationPut.editStation(station.getId(), stationNameField.getText())) {
                showInfoPopup("Станция", "изменена");
                refreshData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        } else {
            showWarningPopup("No selection", "No station selected", "Пожалуйста, выберите станцию для изменения!");
        }
    }

    public void deleteStation() throws IOException {
        ObservableList<Station> list = stationsListView.getSelectionModel().getSelectedItems();
        if (list.size() != 0) {
            for (Station i:list) {
                if (StationDelete.deleteStation(i.getId())) {
                    showInfoPopup("Станция", "удалена");
                    refreshData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            }
        } else {
            showWarningPopup("No selection", "No station selected", "Пожалуйста, выберите станции для удаления!");
        }
    }

    public void addNewTrain() throws IOException {
        LocalTime currTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        Train train = new Train(0L, trainCityDepNumberField.getText(), trainCityArrNumberField.getText(), "", "", currTime, currTime, LocalDate.now(), LocalDate.now());
        boolean okClicked = mainApp.showTrainEditDialog(train);
        if (okClicked) {
            if (TrainPost.addNewTrain(train)) {
                showInfoPopup("Поезд", "добвален");
                searchTrains();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        }
    }

    public void editTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            boolean okClicked = mainApp.showTrainEditDialog(selectedTrain);
            if (okClicked) {
                System.out.println(selectedTrain);
                if (TrainPut.editTrain(selectedTrain)) {
                    showInfoPopup(selectedTrain.toString(), "изменен");
                    searchTrains();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            }
        } else {
            showWarningPopup("No selection", "No train selected", "Пожалуйста, выберите поезд для редактирования!");
        }
    }

    public void deleteTrain() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            if (TrainDelete.deleteTrain(selectedTrain.getId())) {
                showInfoPopup("Поезд", "удален");
                searchTrains();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
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

    public void updateData() {
        refreshData();
    }

    public void openTrainDataPage() throws IOException {
        Train selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            mainApp.showTrainData(selectedTrain);
        } else {
            showWarningPopup("No train selected", "No selection", "Пожалуйста, выберите поезд для показа данных!");
        }
    }
}
