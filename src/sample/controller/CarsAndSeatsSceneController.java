package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Car;
import sample.model.Seat;
import sample.model.Train;
import sample.request.GET.Car.CarParser;

import java.io.IOException;

public class CarsAndSeatsSceneController {

    private Main mainApp;
    private Stage dialogStage;
    private Train train;
    private final ObservableList<Car> carsData = FXCollections.observableArrayList();
    CarParser carParser = new CarParser();

    public TableView<Car> carTableView;
    public TableColumn<Car, Long> carColumn;
    public Button carEditButton;
    public Button carDeleteButton;
    public Button carAddButton;
    public Label carClassLabel;
    public Label carTypeLabel;

    public ListView<Seat> seatsListView;
    public Button seatDeleteButton;
    public Button seatAddButton;

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
        carsData.addAll(carParser.getListOfCars(Long.parseLong(train.getId())));
        carColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        showCarsData(null);

        carTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarsData(newValue)
        );
    }
}
