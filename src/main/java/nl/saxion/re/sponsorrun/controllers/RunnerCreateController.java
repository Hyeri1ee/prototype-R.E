package nl.saxion.re.sponsorrun.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nl.saxion.re.sponsorrun.model.MainWindow;
import nl.saxion.re.sponsorrun.model.PopUpWindow;
import nl.saxion.re.sponsorrun.model.Runner;

public class RunnerCreateController {
    @FXML
    private TextField name;
    @FXML
    private TextField event;
    @FXML
    private TextField email;

    @FXML
    private void onConfirmClick() {
        //delete previous runner's data
        Runner editedRunner = new Runner();

        // get TextField's data and make editedRunner
        editedRunner.setName(name.getText());
        editedRunner.setEvent(event.getText());
        editedRunner.setEmail(email.getText());
        editedRunner.index = MainWindow.runnersList.size();

        MainWindow.runnersList.add(editedRunner);


        //for test
        for (Runner a : MainWindow.runnersList)
        {
            System.out.println(a.name);
            System.out.println(a.email);
        }

        PopUpWindow.popUpWindowStage.close();
    }
}
