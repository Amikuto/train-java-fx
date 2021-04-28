package sample.controller.editors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Station;
import sample.model.Train;
import sample.API.Station.StationParser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class TrainEditController {

    StationParser stationParser = new StationParser();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

    private final ObservableList<Station> stationsDeparting = FXCollections.observableArrayList();
    private final ObservableList<Station> stationsArriving = FXCollections.observableArrayList();

    private Main mainApp;
    private Train train;
    private Stage dialogStage;
    private boolean okClicked = false;

    public DatePicker trainDateDep;
    public DatePicker trainDateArr;
    public TextField trainTimeDep;
    public TextField trainTimeArr;
    public TextField trainDepartingCity;
    public TextField trainArrivingCity;
    public Button trainCancelButton;
    public Button trainOkButton;
    public ChoiceBox<Station> trainDepStation;
    public ChoiceBox<Station> trainArrStation;

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
//        stationsData = mainApp.getStationsData().sorted();
//        trainDepStation.setItems(stationsData);
//        trainArrStation.setItems(stationsData);
    }

    public void setStationsData(){
//        stationsData = mainApp.getStationsData().sorted();
//        Stream<String> stationsData1 = stationsData.sorted().stream().map(Station::getStationName);
//        System.out.println(Arrays.toString(stationsData1.toArray()));
//        trainDepStation.setItems(stationsData);
//        trainArrStation.setItems(stationsData);
//        System.out.println(stationsData);
    }

    @FXML
    private void initialize(){
        trainDateDep.setPromptText(LocalDate.now().toString());
        trainDateArr.setPromptText(LocalDate.now().toString());
        trainTimeDep.setText(LocalTime.of(23, 59).toString());
        trainTimeArr.setText(LocalTime.of(23, 59).toString());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {

            train.setId(0L);

            train.setDateDep(trainDateDep.getValue());
            train.setDateArr(trainDateArr.getValue());

            train.setTimeDep(LocalTime.parse(trainTimeDep.getText()));
            train.setTimeArr(LocalTime.parse(trainTimeArr.getText()));

            train.setDepSt(trainDepStation.getValue().getStationName());
            train.setArrSt(trainArrStation.getValue().getStationName());

            train.setDepartingCity(trainDepartingCity.getText());
            train.setArrivalCity(trainArrivingCity.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (trainDateArr.getValue() == null || isValid(trainDateArr.getValue().toString())) {
            errorMessage += "Указана неверная дата отправления!\n";
        }
        if (trainDateDep.getValue() == null || isValid(trainDateDep.getValue().toString())) {
            errorMessage += "Указана неверная дата прибытия!\n";
        }
        if (trainDepStation.getValue() == null || trainDepStation.getValue().getStationName().length() == 0) {
            errorMessage += "Указано неверное название станции отправления!\n";
        }
        if (trainArrStation.getValue() == null || trainArrStation.getValue().getStationName().length() == 0) {
            errorMessage += "Указано неверное название станции прибытия!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private boolean isValid(String date){
        try {
            return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
        }catch (ParseException ex){
            return false;
        }
    }

    public void getDepStationData() throws IOException {
        if (trainDepartingCity.getText() != null && trainDepartingCity.getText().length() != 0) {
            stationsDeparting.clear();
            stationsDeparting.addAll(stationParser.getAllStationsByCityName(trainDepartingCity.getText()));
            trainDepStation.setItems(stationsDeparting);
        }
    }

    public void getArrStationData() throws IOException {
        if (trainArrivingCity.getText() != null && trainArrivingCity.getText().length() != 0) {
            stationsArriving.clear();
            stationsArriving.addAll(stationParser.getAllStationsByCityName(trainArrivingCity.getText()));
            trainArrStation.setItems(stationsArriving);
        }
    }
}
