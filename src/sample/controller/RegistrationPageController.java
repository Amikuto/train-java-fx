package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.API.User.UserPost;
import sample.Main;
import sample.model.User;

import java.io.IOException;

public class RegistrationPageController {

    private Main mainApp;
    private Stage dialogStage;

    public TextField fullNameLabel;
    public TextField loginLabel;
    public TextField emailLabel;
    public TextField passwordLabel;

    @FXML
    private void initialize(){}

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleCancelButton() {
        dialogStage.close();
    }

    public void handleRegistrationButton() throws IOException {

        String fullName = fullNameLabel.getText();
        String login = loginLabel.getText();
        String email = emailLabel.getText();
        String password = passwordLabel.getText();
        UserPost.addNewUser(fullName, login, email, password);
    }
}
