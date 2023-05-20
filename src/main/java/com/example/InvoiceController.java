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
    private static final Logger loggER = Logger.getLogger(InvoiceController.class.getName());
    @FXML
    private Label address;
    @FXML
    private Label ddate1;

    @FXML
    private Label discount;

    @FXML
    private Label rdate1;
    @FXML
    private Label localprice;
    @FXML
    private Label totalprice1;

    @FXML
    private Label user;
    @FXML
    private TableColumn<Invoice, Double> areacolom1;

    @FXML
    private TableColumn<Invoice, String> pnolom1;

    @FXML
    private TableColumn<Invoice, Double> pricecolom1;

    @FXML
    private TableColumn<Invoice, Double> quantitycolom1;

    @FXML
    private TableView<Invoice> tableviwe1;

    private double discountcalc=0;
    private ConnectionDatabase data;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        pnolom1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        areacolom1.setCellValueFactory(new PropertyValueFactory<>("area"));
        quantitycolom1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricecolom1.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableviwe1.setItems(ProductEntryController.lLIST);
        initializeHelper();
    }
    private void initializeHelper()
    {
        try {
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT SYSDATE from USER_TABLE";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            rs.next();
            rdate1.setText(rs.getString(1));
            str="SELECT SYSDATE+7 from USER_TABLE";
            rs = stmt.executeQuery(str);
            rs.next();
            ddate1.setText(rs.getString(1));
            user.setText(HelloController.getUserNamee());
            address.setText(ProductEntryController.location);


            String dis="SELECT count(CUSTOMER_EMAIL) FROM PRODUCT WHERE CUSTOMER_EMAIL='"+ HelloController.getGmailCounter()+"'";
            Statement sss=con.createStatement();
            ResultSet count=sss.executeQuery(dis);
            count.next();
            discountcalc=count.getDouble(1);
        }
        catch (Exception e)
        {
            loggER.log(Level.WARNING, "Exception in invoice");
        }

        if(discountcalc<=10)discountcalc=0.00;

        else if(discountcalc<=20)discountcalc=ProductEntryController.localprice*0.1;

        else discountcalc=ProductEntryController.localprice*0.2;

        if(ProductEntryController.getLocalPrice()>=1000)discountcalc=ProductEntryController.getLocalPrice()*0.02;
        discount.setText(discountcalc +"$");
        totalprice1.setText(ProductEntryController.getLocalPrice() - discountcalc +"$");
        localprice.setText(ProductEntryController.getLocalPrice() +"$");
        ProductEntryController.setZeroLocalPrice();
        discountcalc=0;
    }

    public void backandclear(MouseEvent event) {
        ProductEntryController.lLIST.clear();
        tableviwe1.setItems(ProductEntryController.lLIST);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Product-view/Product-entry-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) discount.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (Exception e) {
            loggER.log(Level.WARNING, "Exception in Logout Clicked");
        }
    }
    }

