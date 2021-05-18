package sample.controller.editors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Car;

/**
 * Контроллер страницы редактирования вагонов
 * @author damir
 */
public class CarEditController {

    private Car car;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField number;
    public TextField type;
    public TextField trainId;
    public TextField carClass;

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
     * Функция обработчик нажатия кнопка закрытия окна. Закрывает окно
     */
    public void handleExitButton() {
        dialogStage.close();
    }

    /**
     * Установка класса Car {@link Car} для его добавления\редактирования
     * @param car параметры вагона
     */
    public void setCar(Car car) {
        this.car = car;
        number.setText(car.getNumber().toString());
        type.setText(car.getType().toUpperCase());
        trainId.setText(car.getTrainId().toString());
        carClass.setText(car.getCarClass());
    }

    /**
     * Функция обработчик нажатия кнопки ОК.
     * Проверяет правильность введенных данных, меняет статус нажатия кнопки ОК,
     * закрывает окно
     */
    public void handleOkButton() {
        if (isInputValid()) {
            car.setId(car.getId());
            car.setNumber(Integer.parseInt(number.getText()));
            car.setType(type.getText().toLowerCase().toLowerCase());
            car.setTrainId(Integer.parseInt(trainId.getText()));
            car.setCarClass(carClass.getText());

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
