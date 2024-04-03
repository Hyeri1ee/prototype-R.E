package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import nl.saxion.re.sponsorrun.model.MainWindow;
import nl.saxion.re.sponsorrun.model.PopUpWindow;
import nl.saxion.re.sponsorrun.model.Runner;

import java.net.URL;
import java.util.ResourceBundle;

public class RunnerEditController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField event;
    @FXML
    private TextField email;

    @FXML
    private void onConfirmClick() {

        PopUpWindow.runnerToEdit.setName(name.getText());
        PopUpWindow.runnerToEdit.setEvent(event.getText());
        PopUpWindow.runnerToEdit.setEmail(email.getText());

        MainWindow.runnersList.set(PopUpWindow.runnerToEdit.index,PopUpWindow.runnerToEdit);


        PopUpWindow.popUpWindowStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.textProperty().set(PopUpWindow.runnerToEdit.name);
        event.textProperty().set(PopUpWindow.runnerToEdit.event);
        email.textProperty().set(PopUpWindow.runnerToEdit.email);
    }
}
