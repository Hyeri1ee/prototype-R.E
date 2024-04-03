package nl.saxion.re.sponsorrun.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nl.saxion.re.sponsorrun.model.MainWindow;
import nl.saxion.re.sponsorrun.model.PopUpWindow;
import nl.saxion.re.sponsorrun.model.Runner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdministrationController implements Initializable {

    @FXML
    private TableColumn<Runner, String> name;

    @FXML
    private TableColumn<Runner, String> event;

    @FXML
    private TableColumn<Runner, String> email;

    @FXML
    private TableView<Runner> table;

    @FXML
    private Button editButton;

    @FXML
    private Pagination pagination;

    @FXML
    private TextField searchField;

    @FXML
    private Label searchLabel;


    @FXML
    private AnchorPane adminAnchorPane;


    @FXML
    private void onClick1(ActionEvent event) throws IOException {


    }
    @FXML
    private void onClick2() throws IOException {

        MainWindow.selectedRunner = 1;

    }
    @FXML
    private void onClick3() throws IOException {

        MainWindow.selectedRunner = 2;
    }
    @FXML
    private void onClick4() throws IOException {

        MainWindow.selectedRunner = 3;
    }

    @FXML
    private void onCreate(){

        PopUpWindow.loadWindow(

                getClass().getResource("/runnerCreate.fxml"),
                event -> {pageManager();}
        );

    }

    private ObservableList<Runner> initializeData(int startIndex, int endIndex){
        ObservableList<Runner> observableList = FXCollections.observableArrayList();



        if (startIndex > MainWindow.runnersList.size() - 1){
            return  observableList;
        }

        if (endIndex > MainWindow.runnersList.size() - 1){
            endIndex = MainWindow.runnersList.size() - 1;
        }

        MainWindow.currentAmountRunners=0;
        if (!MainWindow.runnersList.isEmpty()){
            for (int i = startIndex; i < endIndex+1; i++) {
                observableList.add(MainWindow.runnersList.get(i));
                MainWindow.currentAmountRunners+=1;
            }
        }
        return observableList;
    }

    private ObservableList<Runner> initializeSearchData(int startIndex, int endIndex,List<Runner> runners){
        ObservableList<Runner> observableList = FXCollections.observableArrayList();



        if (startIndex > runners.size() - 1){
            return  observableList;
        }

        if (endIndex > runners.size() - 1){
            endIndex = runners.size() - 1;
        }

        MainWindow.currentAmountRunners=0;
        if (!runners.isEmpty()){
            for (int i = startIndex; i < endIndex+1; i++) {
                observableList.add(runners.get(i));
                MainWindow.currentAmountRunners+=1;
            }
        }
        return observableList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Runner, String>("name"));
        event.setCellValueFactory(new PropertyValueFactory<Runner, String>("event"));
        email.setCellValueFactory(new PropertyValueFactory<Runner, String>("email"));

        if (!MainWindow.runnersList.isEmpty()){
            pageManager();

        }

        table.getSelectionModel().selectedIndexProperty().addListener((obs,oldIndex,newIndex) -> onRunnerClick());

        pagination.currentPageIndexProperty().addListener((obs,oldIndex,newIndex) -> onPageClick());

        searchField.textProperty().addListener((obs,oldIndex,newIndex) -> onSearchText());
    }

    @FXML
    private void onClickEdit(){

        if (!searchField.textProperty().get().isEmpty()){
            if (MainWindow.currentPage > 0){

                int index = (MainWindow.currentPage * 8) + MainWindow.selectedRunner;
                PopUpWindow.runnerToEdit  = MainWindow.searchRunnerList.get(index);
                PopUpWindow.indexOfSearchedEditRunner= index;
            }
            else
            {
                PopUpWindow.runnerToEdit  = MainWindow.searchRunnerList.get(MainWindow.selectedRunner);
                PopUpWindow.indexOfSearchedEditRunner = MainWindow.selectedRunner;
            }

            PopUpWindow.loadWindow(getClass().getResource(
                            "/runnerEdit.fxml"),
                    event -> {searchPageManager();table.refresh();}
            );
        }
        else{
            if (MainWindow.currentPage > 0){

                int index = (MainWindow.currentPage * 8) + MainWindow.selectedRunner;
                PopUpWindow.runnerToEdit  = MainWindow.runnersList.get(index);
                PopUpWindow.indexOfEditedRunner= index;
            }
            else
            {
                PopUpWindow.runnerToEdit  = MainWindow.runnersList.get(MainWindow.selectedRunner);
                PopUpWindow.indexOfEditedRunner = MainWindow.selectedRunner;
            }

            PopUpWindow.loadWindow(getClass().getResource(
                            "/runnerEdit.fxml"),
                    event -> {pageManager();table.refresh();}
            );
        }


    }

    void onSearchText(){
        if (!searchField.textProperty().get().isEmpty()){
            searchLabel.setVisible(false);

            SearchBarController src = new SearchBarController();

            table.getItems().clear();

//            table.setItems(FXCollections.observableList(src.search(searchField.textProperty().get().toLowerCase())));
            MainWindow.searchRunnerList = src.search(searchField.textProperty().get().toLowerCase());
            searchPageManager();

            System.out.println("search");
        }
        else{
            searchLabel.setVisible(true);
            table.getItems().clear();
            pageManager();
        }
    }

    void onRunnerClick(){

        if (editButton.visibleProperty().get()){
            editButton.setVisible(false);
        }

        MainWindow.selectedRunner = table.getSelectionModel().getSelectedIndex();
        System.out.println(MainWindow.selectedRunner);
        if (MainWindow.selectedRunner < 0){
            return;
        }

        Point2D coords = getEditButtonCoord(MainWindow.selectedRunner);
        editButton.setLayoutX(coords.getX());
        editButton.setLayoutY(coords.getY());
        editButton.setVisible(true);
    }

    void onPageClick(){
        MainWindow.currentPage = pagination.getCurrentPageIndex();
        if (!searchField.textProperty().get().isEmpty()){
            //onSearchText();
            searchPageManager();
        }
        else
        {
            pageManager();
        }


    }

    void searchPageManager(){

        pagination.setMaxPageIndicatorCount((int) Math.ceil(MainWindow.searchRunnerList.size() / 8.0));
        pagination.setPageCount((int) Math.ceil(MainWindow.searchRunnerList.size() / 8.0));

        if (MainWindow.currentPage > 0){

            int startIndex = (MainWindow.currentPage * 8);
            table.setItems(initializeSearchData(startIndex,startIndex+7,MainWindow.searchRunnerList));

        }
        else{
            table.setItems(initializeSearchData(0,7,MainWindow.searchRunnerList));
        }
    }

    void pageManager(){

        pagination.setMaxPageIndicatorCount((int) Math.ceil(MainWindow.runnersList.size() / 8.0));
        pagination.setPageCount((int) Math.ceil(MainWindow.runnersList.size() / 8.0));

        if (MainWindow.currentPage > 0){

            int startIndex = (MainWindow.currentPage * 8);
            table.setItems(initializeData(startIndex,startIndex+7));

        }
        else{
            table.setItems(initializeData(0,7));
        }
    }

    Point2D getEditButtonCoord(int index){
        switch (index){
            case 0:
                return new Point2D(850,250);
            case 1:
                return new Point2D(850,289);
            case 2:
                return new Point2D(850,327);
            case 3:
                return new Point2D(850,364);
            case 4:
                return new Point2D(850,403);
            case 5:
                return new Point2D(850,440);
            case 6:
                return new Point2D(850,479);
            case 7:
                return new Point2D(850,516);
            default:
                return null;

        }
    }
}