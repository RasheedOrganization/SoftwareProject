package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class InvoiceController implements Initializable {
    HelloController h = new HelloController();
    @FXML
    private Label Address;
    @FXML
    private Label D_Date;

    @FXML
    private Label Discount;

    @FXML
    private Label R_Date;
    @FXML
    private Label LocalPrice;
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

    public double Discountcalc=0;
    private ConnectionDatabase Data;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        PN_colom.setCellValueFactory(new PropertyValueFactory<Invoice, String>("ProductName"));
        Area_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("area"));
        Quantity_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("quantity"));
        Price_colom.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("price"));

        Table_viwe.setItems(ProductEntryController.LIST);
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
            str="SELECT SYSDATE+7 from USER_TABLE";
            rs = stmt.executeQuery(str);
            rs.next();
            D_Date.setText(rs.getString(1));
            User.setText(h.getUserNamee());
            Address.setText(ProductEntryController.location);


            String dis="SELECT count(CUSTOMER_EMAIL) FROM PRODUCT WHERE CUSTOMER_EMAIL='"+h.getGmailCounter()+"'";
            Statement sss=con.createStatement();
            ResultSet count=sss.executeQuery(dis);
            count.next();
            Discountcalc=count.getDouble(1);
            System.out.println(Discountcalc);

        }
        catch (Exception e)
        {
            System.out.println("Exception in invoice");
        }

        if(Discountcalc<=10)Discountcalc=0.00;

        else if(Discountcalc<=20)Discountcalc=ProductEntryController.LocalPrice*0.1;

        else Discountcalc=ProductEntryController.LocalPrice*0.2;

        if(ProductEntryController.LocalPrice>=1000)Discountcalc=ProductEntryController.LocalPrice*0.02;
        Discount.setText(Double.toString(Discountcalc)+"$");
        Total_price.setText(Double.toString(ProductEntryController.LocalPrice-Discountcalc)+"$");
        LocalPrice.setText(Double.toString(ProductEntryController.LocalPrice)+"$");
        ProductEntryController.LocalPrice=0;
        Discountcalc=0;

    }

    public void BackAndClear(MouseEvent event) {
        ProductEntryController.LIST.clear();
        Table_viwe.setItems(ProductEntryController.LIST);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Product-view/Product-entry-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Discount.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            System.out.println("Exception in Logout Clicked");
        }
    }
    }

