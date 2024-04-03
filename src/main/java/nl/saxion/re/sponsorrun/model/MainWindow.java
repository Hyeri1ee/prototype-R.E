package nl.saxion.re.sponsorrun.model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow {

    public static ArrayList<Runner> runnersList = new ArrayList<Runner>();
    public static List<Runner> searchRunnerList = new ArrayList<>();
    public static int selectedRunner;
    public static int currentPage = 0;
    public static int currentAmountRunners;

    public static Stage mainWindowStage = new Stage();
    public static Scene mainWindowScene;
    public static Parent mainWindowRoot;


    public static void loadWindow(URL fxml){
        try {
            mainWindowRoot = FXMLLoader.load(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainWindowScene = new Scene(mainWindowRoot);
        mainWindowStage.setScene(mainWindowScene);
        mainWindowStage.setResizable(false);
        mainWindowStage.show();
    }

}
