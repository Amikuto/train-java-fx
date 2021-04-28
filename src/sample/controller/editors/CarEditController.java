package sample.controller.editors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Car;

public class CarEditController {

    private Car car;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField number;
    public TextField type;
    public TextField trainId;
    public TextField carClass;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void handleExitButton() {
        dialogStage.close();
    }

    public void setCar(Car car) {
        this.car = car;
        number.setText(car.getNumber().toString());
        type.setText(car.getType());
        trainId.setText(car.getTrainId().toString());
        carClass.setText(car.getCarClass());
    }

    public void handleOkButton() {
        if (isInputValid()) {
            car.setId(car.getId());
            car.setNumber(Integer.parseInt(number.getText()));
            car.setType(type.getText());
            car.setTrainId(Integer.parseInt(trainId.getText()));
            car.setCarClass(carClass.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (number.getText() == null || number.getText().length() == 0) {
            errorMessage += "Указан неверный номер!\n";
        }
        if (type.getText() == null || type.getText().length() == 0) {
            errorMessage += "Указан неверный тип вагона!\n";
        }
        if (trainId.getText() == null || trainId.getText().length() == 0) {
            errorMessage += "Указан неверный id поезда!\n";
        }
        if (carClass.getText() == null || carClass.getText().length() == 0) {
            errorMessage += "Указан неверный класс вагона!\n";
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
