package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportsController implements Initializable {
    private static final Logger loggER = Logger.getLogger(ReportsController.class.getName());
    @FXML
    private Button backbutton;

    @FXML
    private Button customerReport;

    @FXML
    private Button financialReport;

    @FXML
    private Button productReport;

    @FXML
    private Button rreport;
    ConnectionDatabase data = ConnectionDatabase.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    // this is empty because nothing fucker
    }

    public void vViewClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbutton.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            loggER.log(Level.WARNING, "Exception in Business view button");
        }

    }

    @FXML
    void backclicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbutton.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            loggER.log(Level.WARNING, "Exception in Business view button");
        }
    }



}
