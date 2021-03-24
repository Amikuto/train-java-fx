package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;

import java.io.IOException;

public class WelcomeSceneController {

    private Main mainApp;

    @FXML
    public Button loginButton;

    @FXML
    public Button registrationButton;

    public WelcomeSceneController(){}

    @FXML
    public void showRegistrationPage() throws IOException {
        mainApp.showRegistrationPage();
    }

    @FXML
    public void showLoginPage() throws IOException {
        mainApp.showLoginPage();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
