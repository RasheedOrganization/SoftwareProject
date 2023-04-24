package com.example;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.naming.Binding;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Chart implements Initializable {
    @FXML
    private PieChart C_chart;

    @FXML
    private PieChart I_chart;
    private ConnectionDatabase Data;
    int c=0,i=0,w=0;
    double pw=0,pi=0,pc=0;
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        i=c=w=0;
        pw=pi=pc=0;
        CountHelp();
        PriceHelp();
        ObservableList<PieChart.Data> ItemChart=FXCollections.observableArrayList(
                new PieChart.Data("Complete",c),
                new PieChart.Data("In_Treatment",i),
                new PieChart.Data("Waiting",w)
        );

        I_chart.getData().addAll(ItemChart);
        I_chart.labelsVisibleProperty().set(false);





        ObservableList<PieChart.Data> CashChart=FXCollections.observableArrayList(
                new PieChart.Data("Near Income",pi),
                new PieChart.Data("Budget",pc),
                new PieChart.Data("Far Income",pw)
        );


        C_chart.getData().addAll(CashChart);
        C_chart.labelsVisibleProperty().set(false);
        

    }




    private void PriceHelp() {
        try{
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

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
        catch (Exception e)
        {}
    }

    private void CountHelp() {
        try{
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

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
        catch (Exception e)
        {}
    }

    public void BackFromChart(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) I_chart.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {

        }
    }
}
