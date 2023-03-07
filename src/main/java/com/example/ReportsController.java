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

public class ReportsController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Button CustomerReport;

    @FXML
    private Button FinancialReport;

    @FXML
    private Button ProductReport;

    @FXML
    private Button Report;

    @FXML
    void ReportClicked(ActionEvent event) {
        if(event.getSource() == FinancialReport) {

        }
        else if(event.getSource() == ProductReport) {

        }
        else if(event.getSource() == CustomerReport) {

        }
        else {

        }
    }

    @FXML
    void BackClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) FinancialReport.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
