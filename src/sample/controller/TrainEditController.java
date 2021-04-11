package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Station;
import sample.model.Train;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

public class TrainEditController {

    private Main mainApp;
    ObservableList<Station> stationsData;
    private Train train;
    private Stage dialogStage;
    private boolean okClicked = false;

    public DatePicker trainDateDep;
    public DatePicker trainDateArr;
    public TextField trainTimeDep;
    public TextField trainTimeArr;
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
        stationsData = mainApp.getStationsData().sorted();
//        Stream<String> stationsData1 = stationsData.sorted().stream().map(Station::getStationName);
//        System.out.println(Arrays.toString(stationsData1.toArray()));
        trainDepStation.setItems(stationsData);
        trainArrStation.setItems(stationsData);
    }

    @FXML
    private void initialize(){
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTrain(Train train) {
        this.train = train;

        trainDateDep.setPromptText(LocalDate.now().toString());
        trainDateArr.setPromptText(LocalDate.now().toString());
        trainTimeDep.setText(LocalTime.of(23, 59).toString());
        trainTimeArr.setText(LocalTime.of(23, 59).toString());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {

            train.setDateDep(trainDateDep.getValue());
            train.setDateArr(trainDateArr.getValue());
            train.setTimeDep(LocalTime.parse(trainTimeDep.getText()));
            train.setTimeArr(LocalTime.parse(trainTimeArr.getText()));
            train.setDepSt(trainDepStation.getValue().getId());
            train.setArrSt(trainArrStation.getValue().getId());

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

//        if (stationNameField.getText() == null || stationNameField.getText().length() == 0) {
//            errorMessage += "Указано неверное имя станции!\n";
//        }
//        if (stationCityField.getText() == null || stationCityField.getText().length() == 0) {
//            errorMessage += "Указано неверное название станции!\n";
//        }

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
}
