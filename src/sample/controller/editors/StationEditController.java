package sample.controller.editors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Car;
import sample.model.Station;

/**
 * Контроллер страницы редактирования станций
 * @author damir
 */
public class StationEditController {

    private Station station;
    private Stage dialogStage;
    private boolean okClicked = false;

    public TextField stationCityField;
    public TextField stationNameField;
    public Button cancelButton;
    public Button okButton;

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
     * Установка класса Station {@link Station} для его добавления\редактирования
     * @param station параметры станции
     */
    public void setStation(Station station) {
        this.station = station;

        stationCityField.setText(station.getCityName());
        stationNameField.setText(station.getCityName());
    }

    /**
     * Функция проверки нажатя кнопки ОК
     * @return true если кнопка нажата или false если не нажата
     */
    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * Функция обработчик нажатия кнопки ОК.
     * Проверяет правильность введенных данных, меняет статус нажатия кнопки ОК,
     * закрывает окно
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            station.setStationName(stationNameField.getText());
            station.setCityName(stationCityField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Функция обработчик нажатия кнопка закрытия окна. Закрывает окно
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Функция валидации введенных данных
     * @return возвращает true если данные корректы
     * и false с всплывающем окном, если они не верны.
     */
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
