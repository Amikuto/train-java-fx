package sample.controller.editors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Station;

public class StationEditController {

    private Station station;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField stationCityField;
    public TextField stationNameField;
    public Button cancelButton;
    public Button okButton;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStation(Station station) {
        this.station = station;

        stationCityField.setText(station.getCityName());
        stationNameField.setText(station.getCityName());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            station.setStationName(stationNameField.getText());
            station.setCityName(stationCityField.getText());

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

        if (stationNameField.getText() == null || stationNameField.getText().length() == 0) {
            errorMessage += "Указано неверное имя станции!\n";
        }
        if (stationCityField.getText() == null || stationCityField.getText().length() == 0) {
            errorMessage += "Указано неверное название станции!\n";
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
}
