package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.logging.Level;
import java.util.logging.Logger;


public class InvoiceController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(InvoiceController.class.getName());
    @FXML
    private Label Address;
    @FXML
    private Label DDate1;

    @FXML
    private Label Discount;

    @FXML
    private Label RDate1;
    @FXML
    private Label LocalPrice;
    @FXML
    private Label Totalprice1;

    @FXML
    private Label User;
    @FXML
    private TableColumn<Invoice, Double> Areacolom1;

    @FXML
    private TableColumn<Invoice, String> PN_olom1;

    @FXML
    private TableColumn<Invoice, Double> Pricecolom1;

    @FXML
    private TableColumn<Invoice, Double> Quantitycolom1;

    @FXML
    private TableView<Invoice> Tableviwe1;

    private double Discountcalc=0;
    private ConnectionDatabase Data;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        PN_olom1.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        Areacolom1.setCellValueFactory(new PropertyValueFactory<>("area"));
        Quantitycolom1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Pricecolom1.setCellValueFactory(new PropertyValueFactory<>("price"));

        Tableviwe1.setItems(ProductEntryController.LIST);
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
            RDate1.setText(rs.getString(1));
            str="SELECT SYSDATE+7 from USER_TABLE";
            rs = stmt.executeQuery(str);
            rs.next();
            DDate1.setText(rs.getString(1));
            User.setText(HelloController.getUserNamee());
            Address.setText(ProductEntryController.location);


            String dis="SELECT count(CUSTOMER_EMAIL) FROM PRODUCT WHERE CUSTOMER_EMAIL='"+ HelloController.getGmailCounter()+"'";
            Statement sss=con.createStatement();
            ResultSet count=sss.executeQuery(dis);
            count.next();
            Discountcalc=count.getDouble(1);
        }
        catch (Exception e)
        {
            LOGGER.log(Level.WARNING, "Exception in invoice");
        }

        if(Discountcalc<=10)Discountcalc=0.00;

        else if(Discountcalc<=20)Discountcalc=ProductEntryController.LocalPrice*0.1;

        else Discountcalc=ProductEntryController.LocalPrice*0.2;

        if(ProductEntryController.getLocalPrice()>=1000)Discountcalc=ProductEntryController.getLocalPrice()*0.02;
        Discount.setText(Double.toString(Discountcalc)+"$");
        Totalprice1.setText(Double.toString(ProductEntryController.getLocalPrice()-Discountcalc)+"$");
        LocalPrice.setText(Double.toString(ProductEntryController.getLocalPrice())+"$");
        ProductEntryController.setZeroLocalPrice();
        Discountcalc=0;
    }

    public void BackAndClear(MouseEvent event) {
        ProductEntryController.LIST.clear();
        Tableviwe1.setItems(ProductEntryController.LIST);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Product-view/Product-entry-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Discount.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception in Logout Clicked");
        }
    }
    }

