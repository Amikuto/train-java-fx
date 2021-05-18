package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.API.Car.CarDelete;
import sample.API.Car.CarPost;
import sample.API.Car.CarPut;
import sample.API.Seat.SeatDelete;
import sample.API.Seat.SeatPost;
import sample.API.Seat.SeatPut;
import sample.Main;
import sample.model.Car;
import sample.model.Seat;
import sample.model.Train;
import sample.API.Car.CarParser;

import java.io.IOException;


/**
 * Контроллер сцены вагонов и мест
 * @author damir
 */
public class CarsAndSeatsSceneController {

    private Main mainApp;
    private Stage dialogStage;
    private Train train;
    private final ObservableList<Car> carsData = FXCollections.observableArrayList();
    CarParser carParser = new CarParser();

    public TableView<Car> carTableView;
    public TableColumn<Car, Integer> carColumn;
    public Label carClassLabel;
    public Label carTypeLabel;

    public ListView<Seat> seatsListView;

    /**
     * Конструктор класса
     */
    public CarsAndSeatsSceneController(){}

    /**
     * Получение родительского Main класса для выполнения его функций или получения информации.
     * Установка данных в таблицу вагонов
     * @param mainApp параметр Main класса
     */
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        carTableView.setItems(carsData);
    }

    /**
     * Установка сцены
     * @param dialogStage сцена
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Установка класса Train {@link Train} для его получения его данных
     * @param train параметры вагона
     */
    public void setTrain(Train train){
        this.train = train;
    }

    /**
     * Инициализация класса
     * Устанавливается множественный выбор для мест
     */
    @FXML
    private void initialize(){
        seatsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Метод вывода окна ошибок
     * @param title параметр заглавия (названия)
     * @param header параметр заголовка
     * @param content параметр сообщения
     */
    public static void showWarningPopup(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Метод вывода информационного окна
     * @param text параметр сообщения
     * @param type параметр типа сообщения
     */
    public static void showInfoPopup(String text, String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text + " " + type + "!\n\n\nОбновите данные на странице!");

        alert.showAndWait();
    }

    /**
     * Метод показа информации о вагоне и его местах
     * @param car класс вагона, откуда берется информация
     */
    public void showCarsData(Car car) {
        if (car != null) {
            seatsListView.getItems().clear();
            carClassLabel.setText(car.getCarClass());
            carTypeLabel.setText(car.getType());
            seatsListView.getItems().addAll(car.getSeats());
        } else {
            carClassLabel.setText("");
            carTypeLabel.setText("");
        }
    }

    /**
     * Загрузка информации о вагонах
     * @throws IOException ошибка получения данных с сервера
     */
    public void setData() throws IOException {
        carsData.clear();
        carsData.addAll(carParser.getListOfCars(train.getId()));
        carColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());

        showCarsData(null);

        carTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarsData(newValue)
        );
    }

    /**
     * Метод добавления нового вагона на сервер
     * @throws IOException ошибка получения данных с сервера
     */
    public void addNewCar() throws IOException {
        Car car = new Car(0L, 0, "", "", train.getId(), null);
        boolean okClicked = mainApp.showCarEditDialog(car);
        if (okClicked) {
            if (CarPost.addNewCar(car)) {
                showInfoPopup("Вагон", "добавлен!");
                setData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        }
    }

    /**
     * Метод редактирования вагона на сервере
     * @throws IOException ошибка получения данных с сервера
     */
    public void editCar() throws IOException {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            boolean okClicked = mainApp.showCarEditDialog(selectedCar);
            if (okClicked) {
                if (CarPut.editCar(selectedCar)) {
                    showInfoPopup("Вагон", "изменен!");
                    setData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            }
        }
    }

    /**
     * Метод удаления вагона с сервера
     * @throws IOException ошибка получения данных с сервера
     */
    public void deleteCar() throws IOException {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            if (CarDelete.deleteCar(selectedCar.getId())) {
                showInfoPopup("Вагон", "удален!");
                setData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        } else {
            showWarningPopup("No selection", "No car selected", "Пожалуйста, выберите вагон для удаления!");
        }
    }

    /**
     * Метод добавления нового места на сервер
     * @throws IOException ошибка получения данных с сервера
     */
    public void addNewSeat() throws IOException {
        Seat seat = new Seat(0L, 0, "", 0, carTableView.getSelectionModel().getSelectedItem().getId());
        boolean okClicked = mainApp.showSeatEditDialog(seat);
        if (okClicked) {
            if (SeatPost.addNewSeat(seat)) {
                showInfoPopup("Новое место", "добавлено!");
                setData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        }
    }

    /**
     * Метод редактирования места на сервере
     * @throws IOException ошибка получения данных с сервера
     */
    public void editSeat() throws IOException {
        Seat selectedSeat = seatsListView.getSelectionModel().getSelectedItem();
        if (selectedSeat != null) {
            boolean okClicked = mainApp.showSeatEditDialog(selectedSeat);
            if (okClicked) {
                if (SeatPut.editSeat(selectedSeat)) {
                    showInfoPopup("Данные места", "изменены");
                    setData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            }
        } else {
            showWarningPopup("No selection", "No seat selected", "Пожалуйста, выберите место для удаления!");
        }
    }

    /**
     * Метод удаления места с сервера
     * @throws IOException ошибка получения данных с сервера
     */
    public void deleteSeat() throws IOException {
        ObservableList<Seat> list = seatsListView.getSelectionModel().getSelectedItems();
        if (list.size() != 0) {
            for (Seat selectedSeat:list){
                if (SeatDelete.deleteSeat(selectedSeat.getId())) {
                    showInfoPopup(selectedSeat.toString(), "удалено");
                    setData();
                } else {
                    showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
                }
            }
        } else {
            showWarningPopup("No selection", "No seat selected", "Пожалуйста, выберите место для удаления!");
        }
    }
}
