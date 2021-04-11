package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.request.GET.User.UserGet;

import java.io.IOException;

public class LoginPageController {

    private Main mainApp;
    private Stage dialogStage;
    private boolean CHECKED = false;

    @FXML
    public TextField loginField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button exitButton;

//    LoginPageController() throws IOException {}

    @FXML
    private void initialize(){}


    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isCHECKED(){
        return CHECKED;
    }

    @FXML
    private boolean handleOk() throws IOException {
        if (isInputValid()) {
            String login = loginField.getText();
            Integer password = Integer.parseInt(passwordField.getText());

//            mainApp.addToTest(login, password);

//            mainApp.printTestList();

            if (UserGet.checkPassword(login, password)){
                CHECKED = true;
                dialogStage.close();
                return true;
            } else {
                String errorMessage = "";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Ошибка при вводе данных");
                alert.setHeaderText("Пожалуйста, введите корректный пароль");
                alert.setContentText(errorMessage);

                alert.showAndWait();
            }
        }
        return false;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Указан неверный или пустой логин!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Указан неверный или пустой пароль!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка при вводе данных");
            alert.setHeaderText("Пожалуйста, введите корректные данные");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

//    public void login() {
//        String login = loginField.getText();
//        String password = passwordField.getText();
//    }
//
//    public void exit() {
////        System.out.println(passwordField.getText());
//    }
}
