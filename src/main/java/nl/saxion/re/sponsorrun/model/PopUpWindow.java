package nl.saxion.re.sponsorrun.model;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PopUpWindow {
    public static Stage popUpWindowStage = new Stage();
    public static Scene popUpWindowScene;
    public static Parent popUpWindowRoot;
    public static Runner runnerToEdit;
    public static int indexOfEditedRunner;
    public static int indexOfSearchedEditRunner;

    public static void loadWindow(URL fxml){
        try {
            popUpWindowRoot = FXMLLoader.load(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        popUpWindowScene = new Scene(popUpWindowRoot);
        popUpWindowStage.setScene(popUpWindowScene);
        popUpWindowStage.show();
    }

    public static void loadWindow(URL fxml, EventHandler<WindowEvent> event){
        try {
            popUpWindowRoot = FXMLLoader.load(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        popUpWindowScene = new Scene(popUpWindowRoot);
        popUpWindowStage.setScene(popUpWindowScene);
        popUpWindowStage.setResizable(false);
        popUpWindowStage.setOnHiding(event);
        popUpWindowStage.show();

    }


}
