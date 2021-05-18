package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.API.User.UserParser;
import sample.Main;
import sample.API.User.UserGet;
import sample.model.User;

import java.io.IOException;

/**
 * Контроллер страницы входа пользователя в систему
 * @author damir
 */
public class LoginPageController {

    private Main mainApp;
    private Stage dialogStage;
    private boolean CHECKED = false;
    UserParser userParser = new UserParser();

    @FXML
    public TextField loginField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button exitButton;

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
     * Проверка нажатия кнопки вход
     * @return true если кнопка нажата и данные проверены, false в противном случае
     */
    public boolean isCHECKED(){
        return CHECKED;
    }

    /**
     * Функция обработчик нажатия кнопки вход.
     * Проверяет правильность введенных данных, меняет статус нажатия кнопки ОК,
     * проверяет пароль путем запроса на сервер, устанавливает текущего пользователя
     * закрывает окно
     */
    @FXML
    private boolean handleOk() throws IOException {
        if (isInputValid()) {
            String login = loginField.getText();
            try {
                Integer password = Integer.parseInt(passwordField.getText());
                try {
                    if (UserGet.checkPassword(login, password)) {
                        CHECKED = true;
                        User user = userParser.getUserByLogin(login);
                        mainApp.setCurrentUser(user);
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
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Ошибка при вводе данных");
                    alert.setHeaderText("Пароль не совпадает либо пользователь с данным логином не зарегистрирован!");
                    alert.setContentText("");

                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
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

    /**
     * Функция обработчик нажатия кнопка закрытия окна.
     * Закрывает окно и показывает ошибку, если пользователь так и не вошел в систему
     */
    @FXML
    private void handleCancel() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(dialogStage);
        alert.setTitle("Ошибка при входе в учетную запись");
        alert.setHeaderText("Пожалуйста, войдите в систему");
        alert.setContentText("Данные не будут загружены, пока вы не вошли в систему!");

        alert.showAndWait();
        CHECKED = false;
        dialogStage.close();
    }

    /**
     * Функция валидации введенных данных
     * @return возвращает true если данные корректы
     * и false с всплывающем окном, если они не верны.
     */
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

    /**
     * Показывает окно регистрации
     * @throws IOException ошибка получения данных с сервера
     */
    public void showRegistrationPage() throws IOException {
        mainApp.showRegistrationPage();
    }
}
