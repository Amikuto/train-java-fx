package sample.controller.editors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Seat;

/**
 * Контроллер страницы редактирования мест
 * @author damir
 */
public class SeatEditController {

    private Seat seat;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField numberTextField;
    public TextField costTextField;
    public TextField seatTypeTextField;
    public TextField carIdTextField;

    /**
     * Инициализация класса
     */
    @FXML
    private void initialize(){}

    /**
     * Установка сцены
     * @param dialogStage сцена
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Функция проверки нажатя кнопки ОК
     * @return true если кнопка нажата или false если не нажата
     */
    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * Установка класса Seat {@link Seat} для его добавления\редактирования
     * @param seat параметры места
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
        numberTextField.setText(seat.getNumber().toString());
        costTextField.setText(seat.getCost().toString());
        seatTypeTextField.setText(seat.getSeatType());
        carIdTextField.setText(seat.getCarId().toString());
    }

    /**
     * Функция обработчик нажатия кнопка закрытия окна. Закрывает окно
     */
    public void handleExitButton() {
        this.dialogStage.close();
    }

    /**
     * Функция обработчик нажатия кнопки ОК.
     * Проверяет правильность введенных данных, меняет статус нажатия кнопки ОК,
     * закрывает окно
     */
    public void handleOk() {
        if (isInputValid()) {
            seat.setId(seat.getId());
            seat.setNumber(Integer.parseInt(numberTextField.getText()));
            seat.setCost(Integer.parseInt(costTextField.getText()));
            seat.setSeatType(seatTypeTextField.getText().toLowerCase());
            seat.setCarId(Long.parseLong(carIdTextField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Функция валидации введенных данных
     * @return возвращает true если данные корректы
     * и false с всплывающем окном, если они не верны.
     */
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
