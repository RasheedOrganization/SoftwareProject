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
    private Button BackButton;

    @FXML
    private Button CustomerReport;

    @FXML
    private Button FinancialReport;

    @FXML
    private Button ProductReport;

    @FXML
    private Button Report;
    ConnectionDatabase data = ConnectionDatabase.getInstance();

    @FXML
    void ReportClicked(ActionEvent event) {
        try {

            Connection con = data.getConnectData();
            String qry;
            if(event.getSource() == FinancialReport) {
                qry = "select name_project from project where project_id = ";
            }
            else if(event.getSource() == ProductReport) {
                qry = "select name_project from project where project_id = ";
            }
            else if(event.getSource() == CustomerReport) {
                qry = "select name_project from project where project_id = ";
            }
            else {
                qry = "select name_project from project where project_id = ";
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            rs.next();
            Map<String, Object> parameter = new HashMap<String, Object>();
            //parameter.put("ProjectP1", searchPID.getText());
            //parameter.put("ProjectP2", TextReportPro.getText());
            //parameter.put("ProjectP3", rs.getString(1));//rs.getString(1)


            InputStream input = new FileInputStream(new File("projectReport.jrxml"));
            JasperDesign jd = JRXmlLoader.load(input);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, con);
            //as pdf dirictly
           /* OutputStream os=new FileOutputStream(new File("EmplyeeSUM.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,os);
            os.close();
            input.close();*/
            //as JFrame
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jp));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public void AddClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Product-view/Product-entry-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) FinancialReport.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {

        }
    }

    public void ViewClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) FinancialReport.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {

        }

    }
}
