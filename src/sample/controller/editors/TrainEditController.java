package sample.controller.editors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Car;
import sample.model.Station;
import sample.model.Train;
import sample.API.Station.StationParser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Контроллер страницы редактирования поездов
 * @author damir
 */
public class TrainEditController {

    StationParser stationParser = new StationParser();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

    private final ObservableList<Station> stationsDeparting = FXCollections.observableArrayList();
    private final ObservableList<Station> stationsArriving = FXCollections.observableArrayList();

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

    /**
     * Инициализация класса
     * Устанавливает текст в полях времени на 23:59
     */
    @FXML
    private void initialize(){
        trainTimeDep.setText(LocalTime.of(23, 59).toString());
        trainTimeArr.setText(LocalTime.of(23, 59).toString());
    }

    /**
     * Установка сцены
     * @param dialogStage сцена
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Установка класса Train {@link Train} для его добавления\редактирования
     * @param train параметры поезда
     */
    public void setTrain(Train train) {
        this.train = train;
        trainDateDep.setValue(this.train.getDateDep());
        trainDateArr.setValue(this.train.getDateArr());
        trainTimeDep.setText(this.train.getTimeDep().toString());
        trainTimeArr.setText(this.train.getTimeArr().toString());
        trainDepartingCity.setText(this.train.getDepartingCity());
        trainArrivingCity.setText(this.train.getArrivalCity());
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

            train.setId(train.getId());

            train.setDateDep(trainDateDep.getValue());
            train.setDateArr(trainDateArr.getValue());

            try {
                train.setTimeDep(LocalTime.parse(trainTimeDep.getText()));
                train.setTimeArr(LocalTime.parse(trainTimeArr.getText()));
            } catch (DateTimeParseException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Ошибка при вводе данных");
                alert.setHeaderText("Данные введены неверно!");
                alert.setContentText("Ошибка в воде данных времени. Введено текущее время!");
                alert.showAndWait();
                okClicked = false;
            }

            train.setDepSt(trainDepStation.getValue().getStationName());
            train.setArrSt(trainArrStation.getValue().getStationName());

            train.setDepartingCity(trainDepartingCity.getText());
            train.setArrivalCity(trainArrivingCity.getText());

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
        if (trainTimeDep.getText() == null || trainTimeDep.getText().length() == 0){
            errorMessage += "Указано неверное время отправления!\n";
        }
        if (trainTimeArr.getText() == null || trainTimeArr.getText().length() == 0){
            errorMessage += "Указано неверное время отправления!\n";
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

    /**
     * Функция проверки введенной даты
     * @param date параметр даты для проверки
     * @return возвращает true при правильно введенной дате
     */
    private boolean isValid(String date){
        try {
            return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
        }catch (ParseException ex){
            return false;
        }
    }

    /**
     * Функция получения станций отправления для города отправления
     * @throws IOException ошибка запроса данных у сервера
     */
    public void getDepStationData() throws IOException {
        if (trainDepartingCity.getText() != null && trainDepartingCity.getText().length() != 0) {
            stationsDeparting.clear();
            stationsDeparting.addAll(stationParser.getAllStationsByCityName(trainDepartingCity.getText()));
            trainDepStation.setItems(stationsDeparting);
        }
    }

    /**
     * Функция получения станций прибытия для города прибытия
     * @throws IOException ошибка запроса данных у сервера
     */
    public void getArrStationData() throws IOException {
        if (trainArrivingCity.getText() != null && trainArrivingCity.getText().length() != 0) {
            stationsArriving.clear();
            stationsArriving.addAll(stationParser.getAllStationsByCityName(trainArrivingCity.getText()));
            trainArrStation.setItems(stationsArriving);
        }
    }
}
