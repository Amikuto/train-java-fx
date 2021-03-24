package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

public class LoginPageController {

    private Main mainApp;
    private Stage dialogStage;
    private boolean okClicked = false;

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

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String login = loginField.getText();
            String password = passwordField.getText();

            mainApp.addToTest(login, password);

            mainApp.printTestList();

            okClicked = true;
            dialogStage.close();
        }
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
