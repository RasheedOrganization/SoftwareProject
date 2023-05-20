package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML
    private Button backbutton;

    @FXML
    private Button CustomerReport;

    @FXML
    private Button FinancialReport;

    @FXML
    private Button ProductReport;

    @FXML
    private Button Report;
    ConnectionDatabase data = ConnectionDatabase.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void vViewClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbutton.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            System.out.println("Exception in Business view button");
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

        }
    }



}
