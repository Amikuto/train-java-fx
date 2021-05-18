package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.API.User.UserPost;
import sample.Main;

import java.io.IOException;

/**
 * Контроллер страницы регистрации пользователя в системе
 * @author damir
 */
public class RegistrationPageController {

    private Main mainApp;
    private Stage dialogStage;

    public TextField fullNameLabel;
    public TextField loginLabel;
    public TextField emailLabel;
    public TextField passwordLabel;

    /**
     * Инициализация класса
     */
    @FXML
    private void initialize(){}

    /**
     * Получение родительского Main класса для выполнения его функций или получения информации.
     * @param mainApp параметр Main класса
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Установка сцены
     * @param dialogStage сцена
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Функция обработчик нажатия кнопка закрытия окна. Закрывает окно
     */
    public void handleCancelButton() {
        dialogStage.close();
    }

    /**
     * Функция обработчик нажатия кнопки регистрация.
     * Регистрирует пользователя на сервере
     */
    public void handleRegistrationButton() throws IOException {
        String fullName = fullNameLabel.getText();
        String login = loginLabel.getText();
        String email = emailLabel.getText();
        String password = passwordLabel.getText();
        if (UserPost.addNewUser(fullName, login, email, password)) {
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка при вводе данных");
            alert.setHeaderText("Пользователь не зарегистрирован!");
            alert.setContentText("");

            alert.showAndWait();
        }
    }
}
