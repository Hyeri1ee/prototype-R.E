module nl.saxion.re.sponsorrun {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;

    requires org.controlsfx.controls;
    requires static lombok;

    opens nl.saxion.re.sponsorrun to javafx.fxml;
    exports nl.saxion.re.sponsorrun;
    exports nl.saxion.re.sponsorrun.controllers;
    opens nl.saxion.re.sponsorrun.controllers to javafx.fxml;
    exports nl.saxion.re.sponsorrun.model;
    opens nl.saxion.re.sponsorrun.model to javafx.fxml;
}