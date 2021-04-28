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
import sample.API.Train.TrainDelete;
import sample.API.Train.TrainPost;
import sample.Main;
import sample.model.Car;
import sample.model.Seat;
import sample.model.Train;
import sample.API.Car.CarParser;

import java.io.IOException;

public class CarsAndSeatsSceneController {

    private Main mainApp;
    private Stage dialogStage;
    private Train train;
    private final ObservableList<Car> carsData = FXCollections.observableArrayList();
    CarParser carParser = new CarParser();

    public TableView<Car> carTableView;
    public TableColumn<Car, Integer> carColumn;
//    public Button carEditButton;
//    public Button carDeleteButton;
//    public Button carAddButton;
    public Label carClassLabel;
    public Label carTypeLabel;

    public ListView<Seat> seatsListView;
//    public Button seatDeleteButton;
//    public Button seatAddButton;

    public CarsAndSeatsSceneController(){}

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        carTableView.setItems(carsData);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTrain(Train train){
        this.train = train;
    }

    @FXML
    private void initialize(){
        seatsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public static void showWarningPopup(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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

    public void setData() throws IOException {
        carsData.clear();
        carsData.addAll(carParser.getListOfCars(train.getId()));
        carColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());

        showCarsData(null);

        carTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarsData(newValue)
        );
    }

    public void addNewCar() throws IOException {
        Car car = new Car(0L, 0, "", "", train.getId(), null);
        boolean okClicked = mainApp.showCarEditDialog(car);
        if (okClicked) {
            if (CarPost.addNewCar(car.getNumber(), car.getType(), car.getTrainId(), car.getCarClass()) == 200) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(car + " добавлен!\n\n\nОбновите данные на странице!");

                alert.showAndWait();

                setData();
            } else {
                showWarningPopup("Ошибка", "Ответ сервера", "Ошибка в веденных данных (возможно, данный id уже занят, проверьте правильность введенных данных либо повторите попытку позже)");
            }
        }
    }

    public void editCar() throws IOException {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            boolean okClicked = mainApp.showCarEditDialog(selectedCar);
            if (okClicked) {
                CarPut.editCar(
                        selectedCar.getId(),
                        selectedCar.getNumber(),
                        selectedCar.getType(),
                        selectedCar.getTrainId(),
                        selectedCar.getCarClass()
                );
            }
        }
    }

    public void deleteCar() throws IOException {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            CarDelete.deleteCar(selectedCar.getId());
            setData();
        } else {
            showWarningPopup("No selection", "No train selected", "Пожалуйста, выберите поезд для удаления!");
        }
    }

    public void editSeat(ActionEvent actionEvent) {
    }

    public void deleteSeat(ActionEvent actionEvent) {
    }

    public void addNewSeat(ActionEvent actionEvent) {

    }
}
