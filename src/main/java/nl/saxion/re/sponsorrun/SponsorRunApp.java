package nl.saxion.re.sponsorrun;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.saxion.re.sponsorrun.controllers.DataController;
import nl.saxion.re.sponsorrun.model.MainWindow;

public class SponsorRunApp extends Application {
    @Override
    public void start(Stage stage){


        // read all data from disk
        DataController.updateFromDisk();

        MainWindow.loadWindow(getClass().getResource("/administration.fxml"));

    }

    public static void main(String[] args) {
        launch();
    }
}