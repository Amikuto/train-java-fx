package sample.controller;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Station;
import sample.model.Train;

import java.io.IOException;

public class WelcomeSceneController {

    @FXML
    public TableView<Station> stationTableView;
    @FXML
    public TableColumn<Station, String> idColumn;
    @FXML
    public TableColumn<Station, String> cityNameColumn;
    @FXML
    public TableColumn<Station, String> stationNameColumn;

    private Main mainApp;

    public WelcomeSceneController(){}

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        stationTableView.setItems(mainApp.getStationsData());
    }

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        cityNameColumn.setCellValueFactory(cellData -> cellData.getValue().cityNameProperty());
        stationNameColumn.setCellValueFactory(cellData -> cellData.getValue().stationNameProperty());
    }
}
