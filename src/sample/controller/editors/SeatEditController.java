package sample.controller.editors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Seat;

public class SeatEditController {

    private Seat seat;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField numberTextField;
    public TextField costTextField;
    public TextField seatTypeTextField;
    public TextField carIdTextField;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
        numberTextField.setText(seat.getNumber().toString());
        costTextField.setText(seat.getCost().toString());
        seatTypeTextField.setText(seat.getSeatType());
        carIdTextField.setText(seat.getCarId().toString());
    }

    public void handleExitButton() {
        this.dialogStage.close();
    }

    public void handleOk() {
        if (isInputValid()) {
            seat.setId(seat.getId());
            seat.setNumber(Integer.parseInt(numberTextField.getText()));
            seat.setCost(Integer.parseInt(costTextField.getText()));
            seat.setSeatType(seatTypeTextField.getText());
            seat.setCarId(Long.parseLong(carIdTextField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (numberTextField.getText() == null || numberTextField.getText().length() == 0) {
            errorMessage += "Указан неверный номер!\n";
        }
        if (costTextField.getText() == null || costTextField.getText().length() == 0) {
            errorMessage += "Указан неверная стоимость!\n";
        }
        if (carIdTextField.getText() == null || carIdTextField.getText().length() == 0) {
            errorMessage += "Указан неверный id вагона!\n";
        }
        if (seatTypeTextField.getText() == null || seatTypeTextField.getText().length() == 0) {
            errorMessage += "Указан неверный тип места!\n";
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
