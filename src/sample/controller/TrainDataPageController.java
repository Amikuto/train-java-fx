package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.API.Train.TrainGet;
import sample.API.Train.TrainParser;

import java.io.IOException;

/**
 * Контроллер страницы статистики поезда
 * @author damir
 */
public class TrainDataPageController {
    private Stage dialogStage;

    public Text textArea;

    /**
     * Инициализация класса
     */
    @FXML
    private void initialize() {
    }

    /**
     * Установка информации в поле данных
     * @param trainId параметр id поезда
     * @throws IOException ошибка получения данных с сервера
     */
    public void setTextArea(Long trainId) throws IOException {
        TrainGet trainGet = new TrainGet();
        String soldTickets = trainGet.trainGetSoldTicketData(trainId);
        String allTickets = trainGet.trainGetNotSoldTicketData(trainId);
        String notSoldTickets = trainGet.trainGetAllTicketData(trainId);
        String thisYearData = trainGet.trainGetCountYearsStatistic(2021);
        String pastYearData = trainGet.trainGetCountYearsStatistic(2020);
        String out = "Всего билетов: " + allTickets + "\nПродано билетов: " + soldTickets + "\nОсталось билетов: " + notSoldTickets +
                "\n\n\nGLOBAL DATA:\n Количество маршрутов в этом году: " + thisYearData + "\n Количество маршрутов в предыдущем году: " + pastYearData +
                "\n Увеличение или уменьшение маршрутов на: " + (Integer.parseInt(thisYearData) - Integer.parseInt(pastYearData));
        textArea.setText(out);
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
    public void handleCloseButton() {
        dialogStage.close();
    }
}
