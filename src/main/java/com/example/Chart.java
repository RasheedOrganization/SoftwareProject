package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Chart implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(Chart.class.getName());
    @FXML
    private PieChart cchart;

    @FXML
    private PieChart ichart;
    private ConnectionDatabase data;
    int c=0;
    int i=0;
    int w=0;
    double pw=0;
    double pi=0;
    double pc=0;
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        i=c=w=0;
        pw=pi=pc=0;
        CountHelp();
        PriceHelp();
        ObservableList<PieChart.data> ItemChart=FXCollections.observableArrayList(
                new PieChart.data("Complete",c),
                new PieChart.data("In_Treatment",i),
                new PieChart.data("Waiting",w)
        );

        ichart.getData().addAll(ItemChart);
        ichart.labelsVisibleProperty().set(false);





        ObservableList<PieChart.data> CashChart=FXCollections.observableArrayList(
                new PieChart.data("Near Income",pi),
                new PieChart.data("Budget",pc),
                new PieChart.data("Far Income",pw)
        );


        cchart.getData().addAll(CashChart);
        cchart.labelsVisibleProperty().set(false);
        

    }




    private void PriceHelp() {
        try{
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT STATUS,PRICE from PRODUCT";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                if(rs.getString(1).equals("COMPLETE"))
                    pc+=rs.getDouble(2);
                if(rs.getString(1).equals("IN_TREATMENT"))
                    pi+=rs.getDouble(2);
                if(rs.getString(1).equals("WAITING"))
                    pw+=rs.getDouble(2);
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in PriceHelp");
        }
    }

    private void CountHelp() {
        try{
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT STATUS from PRODUCT";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                if(rs.getString(1).equals("COMPLETE"))
                    c++;
                if(rs.getString(1).equals("IN_TREATMENT"))
                    i++;
                if(rs.getString(1).equals("WAITING"))
                    w++;
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in CountHelp");
        }
    }

    public void BackFromChart(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ichart.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception in BackFromChart");
        }
    }
}
