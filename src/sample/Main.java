package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controller.*;
import sample.controller.editors.CarEditController;
import sample.controller.editors.SeatEditController;
import sample.controller.editors.StationEditController;
import sample.controller.editors.TrainEditController;
import sample.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Главный класс запуска приложения
 * @author damir
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private User currentUser;

    /**
     * Функция инициализации приложения
     * @param primaryStage главная сцена
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Train application!");
        this.primaryStage.setWidth(1200);
        this.primaryStage.setHeight(750);
//        this.primaryStage.setMaximized(true);
//        this.primaryStage.setResizable(false);

        // Set logo on the top left
        InputStream iconStream = getClass().getResourceAsStream("/static/TLogo.jpeg");
        assert iconStream != null;
        Image image = new Image(iconStream);
        this.primaryStage.getIcons().add(image);

        showRootLayout();

        if (this.showLoginPage()){
            showWelcomeScene();
        }
    }

    /**
     * Функция показа окна городов, станций и поездов
     * @throws IOException ошибка запроса на сервер
     */
    public void showWelcomeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/StationsAndTrainsScene.fxml"));
        AnchorPane welcomeScene = loader.load();

        rootLayout.setCenter(welcomeScene);

        StationsAndTrainsSceneController controller = loader.getController();
        controller.setMainApp(this);
        loader.setController(new RootLayoutController());
    }

    /**
     * Функция показа вагонов и мест
     * @param train параметр поезда {@link Train}, по которому показать информацию
     * @throws IOException ошибка запроса на сервер
     */
    public void showCarsAndSeatsScene(Train train) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/CarsAndSeatsScene.fxml"));
        AnchorPane carsAndSeatsScene = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование поездов");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(carsAndSeatsScene);
        dialogStage.setScene(scene);

        CarsAndSeatsSceneController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTrain(train);
        controller.setMainApp(this);
        loader.setController(new RootLayoutController());
        controller.setData();
        dialogStage.showAndWait();
    }

    /**
     * Функция показа окна регнистрации
     * @throws IOException ошибка запроса на сервер
     */
    public void showRegistrationPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/RegistrationPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Регистрация нового пользователя");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        RegistrationPageController controller = loader.getController();
        controller.setMainApp(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }

    /**
     * Функция показа окна редактирования станций {@link Station}
     * @return возвращает нажатие кнопки ОК
     * @throws IOException ошибка запроса на сервер
     */
    public boolean showStationEditDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/EditStationPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование станций");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        StationEditController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    /**
     * Функция показа окна редактирования или добавления поездов
     * @param train принимает поезд {@link Train} для редактирования
     * @return возвращает нажатие кнопки ОК
     * @throws IOException ошибка запроса на сервер
     */
    public boolean showTrainEditDialog(Train train) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/EditTrainPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование поездов");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        TrainEditController controller = loader.getController();
//        controller.setMainApp(this);
//        controller.setStationsData();
        controller.setDialogStage(dialogStage);
        controller.setTrain(train);

        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    /**
     * Функция показа окна редактирования или добавления вагонов
     * @param car принимает вагон {@link Car} для редактирования или добавления нового
     * @return возвращает нажатие кнопки ОК
     * @throws IOException ошибка запроса на сервер
     */
    public boolean showCarEditDialog(Car car) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/EditCarPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование вагонов");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CarEditController controller = loader.getController();
        controller.setCar(car);
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
        return controller.isOkClicked();
    }

    /**
     * Функция показа окна редактирования или добавления мест
     * @param seat принимает место {@link Seat} для редактирования или добавления нового
     * @return возвращает нажатие кнопки ОК
     * @throws IOException ошибка запроса на сервер
     */
    public boolean showSeatEditDialog(Seat seat) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/EditSeatPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование вагонов");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        SeatEditController controller = loader.getController();
        controller.setSeat(seat);
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
        return controller.isOkClicked();
    }

    /**
     * Функция показа окна статистических данных о поездах
     * @param train принимает поезд {@link Train} для отображения информации
     * @throws IOException ошибка запроса на сервер
     */
    public void showTrainData(Train train) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/TrainDataPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Статистические данные о поезде");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        TrainDataPageController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTextArea(train.getId());

        dialogStage.showAndWait();
    }

    /**
     * Функция показа окна регистрации пользователя {@link User}
     * @return возвращает нажатие кнопки ОК
     * @throws IOException ошибка запроса на сервер
     */
    public boolean showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/LoginPage.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Вход в систему");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        LoginPageController controller = loader.getController();
        controller.setMainApp(this);
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isCHECKED();
    }

    /**
     * Функция показа верхней панели приложения
     * @throws IOException ошибка запроса на сервер
     */
    public void showRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/RootLayout.fxml"));
        rootLayout = loader.load();

        RootLayoutController controller = loader.getController();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        controller.setMainApp(this);
        primaryStage.show();
    }

    /**
     * Функция для передачи дочерним классам главной сцены
     * @return возвращает главную сцену {@link Main#primaryStage}
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Конструктор
     */
    public Main(){
    }

    /**
     * Функция запуска приложения
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Функция для задания текущего пользователя
     * @param currentUser класс User {@link User}
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Функция для получения текущего пользователя
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
