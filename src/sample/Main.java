package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controller.LoginPageController;
import sample.controller.WelcomeSceneController;
import sample.controller.RegistrationPageController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Main extends Application {

    private final HashMap<String, String> test = new HashMap<>();
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Train application!");
        this.primaryStage.setMaximized(true);

        // Set logo on the top left
        InputStream iconStream = getClass().getResourceAsStream("/static/TLogo.jpeg");
        Image image = new Image(iconStream);
        this.primaryStage.getIcons().add(image);

        showRootLayout();

        showWelcomeScene();
    }

    public void showWelcomeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/WelcomeScene.fxml"));
        AnchorPane welcomeScene = loader.load();

        rootLayout.setCenter(welcomeScene);

        WelcomeSceneController controller = loader.getController();
        controller.setMainApp(this);
//        loader.setController(new RootLayoutController());
    }

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
//        controller.set


        dialogStage.showAndWait();
    }

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

        return controller.isOkClicked();
    }

    public void showRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/RootLayout.fxml"));
        rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addToTest(String login, String password) {
        this.test.put(login, password);
    }

    public void printTestList() {
        System.out.println(this.test);
    }
}
