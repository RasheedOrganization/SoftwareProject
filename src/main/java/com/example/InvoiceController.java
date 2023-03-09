package com.example;

import com.example.TableView.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;


public class InvoiceController implements Initializable {
    @FXML
    private Label Address;
    @FXML
    private Label D_Date;

    @FXML
    private Label Discount;

    @FXML
    private Label R_Date;

    @FXML
    private Label Total_price;

    @FXML
    private Label User;
    @FXML
    private TableColumn<Invoice, Double> Area_colom;

    @FXML
    private TableColumn<Invoice, String> PN_colom;

    @FXML
    private TableColumn<Invoice, Double> Price_colom;

    @FXML
    private TableColumn<Invoice, Double> Quantity_colom;

    @FXML
    private TableView<Invoice> Table_viwe;
    private ConnectionDatabase Data;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        PN_colom.setCellValueFactory(new PropertyValueFactory<Invoice, String>("ProductName"));
        Area_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("area"));
        Quantity_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("quantity"));
        Price_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("price"));

        Table_viwe.setItems(ProductEntryController.LIST);
        //Table_viwe.getItems().addAll(ProductEntryController.LIST);
        System.out.print(ProductEntryController.LIST.get(1).getArea());
        InitializeHelper();
    }
    private void InitializeHelper()
    {
        try {
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();
            String str="SELECT SYSDATE from USER_TABLE";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            rs.next();
            R_Date.setText(rs.getString(1));
            str="SELECT SYSDATE+3 from USER_TABLE";
            rs = stmt.executeQuery(str);
            rs.next();
            D_Date.setText(rs.getString(1));
        }
        catch (Exception e)
        {
            System.out.println("Exception in invoice");
        }
        Discount.setText("0.00$");
        Total_price.setText(Double.toString(ProductEntryController.LocalPrice)+"$");
        ProductEntryController.LocalPrice=0;

    }

}
