package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

/**
 * Контроллер верхнего каркаса приложения
 * @author damir
 */
public class RootLayoutController {

    Stage primaryApp;

    /**
     * Получение родительского Main класса для выполнения его функций или получения информации.
     * Установка данных в таблицу вагонов
     * @param mainApp параметр Main класса
     */
    public void setMainApp(Main mainApp) {
        this.primaryApp = mainApp.getPrimaryStage();
    }

    /**
     * Функция обработчик нажатия кнопки закрыть. Закрывает приложение
     */
    public void closeProgram() {
        this.primaryApp.close();
    }

    /**
     * Функция показа окна об Авторе
     * @throws IOException ошибка получения данных с сервера
     */
    public void showAuthorInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/AboutPage.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("About");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.primaryApp);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
}
