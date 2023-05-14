package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BusinessController implements Initializable {
    private static final String DELETEDSUCCESSFULLY = "Deleted successfully";
    private static final String NOTHINGDELETED = "Nothing deleted";
    private static final Logger LOGGER = Logger.getLogger(BusinessController.class.getName());
    @FXML
    private TableView<Customer> customertv;

    @FXML
    private TableView<Product> producttv;
    @FXML
    private TableView<Worker> workertv;

    @FXML
    private TextField tfsearch1;

    @FXML
    private TableColumn<Customer, String> tvcustomeremail;

    @FXML
    private TableColumn<Customer, String> tvcustomername;

    @FXML
    private TableColumn<Customer, String> tvcustomerphone;

    @FXML
    private TableColumn<Product, String> tvpid;

    @FXML
    private TableColumn<Product, String> tvpaddress;

    @FXML
    private TableColumn<Product, String> tvparea;

    @FXML
    private TableColumn<Product, String> tvpdate;

    @FXML
    private TableColumn<Product, String> tvpname;

    @FXML
    private TableColumn<Product, Float> tvpprice;

    @FXML
    private TableColumn<Product, String> tvpquantity;

    @FXML
    private TableColumn<Product, String> tvpstatus;

    @FXML
    private TableColumn<Worker, String> tvwid;

    @FXML
    private TableColumn<Worker, String> tvwflag;

    @FXML
    private TableColumn<Worker, String> tvwname;

    @FXML
    private TableColumn<Worker, String> tvwphone;


    @FXML
    static ObservableList<Customer> clist = FXCollections.observableArrayList();
    @FXML
    static ObservableList<Product> plist = FXCollections.observableArrayList();
    @FXML
    static ObservableList<Worker> wlist = FXCollections.observableArrayList();




    private ConnectionDatabase data;

    public void initialize(URL url, ResourceBundle resourceBundle){
        tvcustomername.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tvcustomeremail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tvcustomerphone.setCellValueFactory(new PropertyValueFactory<>("Phone"));




        tvpid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tvpname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tvparea.setCellValueFactory(new PropertyValueFactory<>("Area"));
        tvpquantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tvpaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tvpstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tvpdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tvpprice.setCellValueFactory(new PropertyValueFactory<>("Price"));



        tvwid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tvwname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tvwphone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tvwflag.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        customerhelper();
        customertv.setVisible(true);
        producttv.setVisible(false);
        workertv.setVisible(false);









    }

    private void workersearch() {
        FilteredList<Worker>filter=new FilteredList<>(wlist, b ->true);
        tfsearch1.textProperty().addListener((observable,oldval,newval)-> {
            filter.setPredicate(worker->{
                if(newval.isEmpty() || newval.isBlank() || newval==null)
                {
                    return true;
                }
                String searchKey=newval.toLowerCase();
                if(worker.getID().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(worker.getName().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(worker.getPhone().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else
                {
                    return worker.getAvailability().toLowerCase().contains(searchKey);
                }

            });

        });
        SortedList<Worker> sortedData=new SortedList<>(filter);
        sortedData.comparatorProperty().bind(workertv.comparatorProperty());
        workertv.setItems(sortedData);


    }
    private void ProductSearch() {
        FilteredList<Product>filter=new FilteredList<>(plist, b ->true);
        tfsearch1.textProperty().addListener((observable,oldval,newval)-> {
            filter.setPredicate(product->{
                if(newval.isEmpty() || newval.isBlank() || newval==null)
                {
                    return true;
                }
                String searchKey=newval.toLowerCase();
                if(product.getID().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(product.getName().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(product.getAddress().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(product.getArea().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(product.getStatus().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(product.getQuantity().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else return product.getDate().toLowerCase().contains(searchKey);

            });

        });
        SortedList<Product> sortedData=new SortedList<>(filter);
        sortedData.comparatorProperty().bind(producttv.comparatorProperty());
        producttv.setItems(sortedData);
    }
    private void customersearch() {
        FilteredList<Customer>filter=new FilteredList<>(clist, b ->true);
        tfsearch1.textProperty().addListener((observable,oldval,newval)-> {
            filter.setPredicate(customer->{
                if(newval.isEmpty() || newval.isBlank())
                {
                    return true;
                }
                String searchKey=newval.toLowerCase();
                if(customer.getName().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else if(customer.getEmail().toLowerCase().contains(searchKey))
                {
                    return true;
                }
                else return customer.getPhone().toLowerCase().contains(searchKey);

            });

        });
        SortedList<Customer> sortedData=new SortedList<>(filter);
        sortedData.comparatorProperty().bind(customertv.comparatorProperty());
        customertv.setItems(sortedData);
    }


    private void customerhelper() {
        clist.clear();
        try {
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT NAME_USER,EMAIL_USER,PHONE_USER from USER_TABLE";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                clist.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3)));
            }

            customertv.setItems(clist);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Customer Table");
        }
        customertv.setVisible(true);
        producttv.setVisible(false);
        workertv.setVisible(false);
        customersearch();
    }
    private void ProductHelper() {
        plist.clear();
        try {
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT PRODUCTID,PRICE,PRODUCTNAME,PRODUCTAREA,QUANTITY,address,STATUS,STRINGDATE from PRODUCT";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                double f=Math.round(rs.getDouble(2) * 1000) / 1000.0;
                plist.add(new Product
                        (
                        rs.getString(1),
                        f                         ,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                        )
                );
            }

            producttv.setItems(plist);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Product Table");
        }
        customertv.setVisible(false);
        producttv.setVisible(true);
        workertv.setVisible(false);
        ProductSearch();
    }
    private void WorkerHelper() {
        wlist.clear();
        try {
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="SELECT ID,NAME,PHONENUMBER,WORKFLAG from WORKERS";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                wlist.add(new Worker(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }

            workertv.setItems(wlist);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Worker Table");
        }
        customertv.setVisible(false);
        producttv.setVisible(false);
        workertv.setVisible(true);
        workersearch();
    }


    public void btnpclicked1(ActionEvent actionEvent) {
        ProductHelper();
    }

    public void btncclicked1(ActionEvent actionEvent) {
        customerhelper();
    }

    public void btnwclicked1(ActionEvent actionEvent) {
        WorkerHelper();
    }

    public void isDeleted(int deleted) {
        if(deleted > 0)
            JOptionPane.showMessageDialog(null , DELETEDSUCCESSFULLY);
        else
            JOptionPane.showMessageDialog(null , NOTHINGDELETED);
    }

    public void btndeleteclicked(ActionEvent actionEvent)
    {
        if(producttv.isVisible())
        {
                try{
                    data=ConnectionDatabase.getInstance();
                    Connection con = data.getConnectData();
                    String P_ID=producttv.getSelectionModel().getSelectedItem().getID();
                    String str="DELETE FROM Product WHERE PRODUCTID='"+P_ID+"'";
                    Statement stmt = con.createStatement();
                    isDeleted(stmt.executeUpdate(str));
                }
                catch (SQLException e)
                {
                    LOGGER.log(Level.WARNING, "Exception in Product delete");
                }

                ProductEntryController.StatusHelper();

                producttv.getItems().removeAll(producttv.getSelectionModel().getSelectedItem());

        }
        else if(workertv.isVisible())
        {
            try{
                data=ConnectionDatabase.getInstance();
                Connection con = data.getConnectData();
                String W_ID=workertv.getSelectionModel().getSelectedItem().getID();
                String str="DELETE FROM WORKERS WHERE ID='"+W_ID+"'";
                Statement stmt = con.createStatement();
                isDeleted(stmt.executeUpdate(str));
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Exception in Worker delete");
            }

            workertv.getItems().removeAll(workertv.getSelectionModel().getSelectedItem());
        }
        else if(customertv.isVisible())
        {
            try{
                data=ConnectionDatabase.getInstance();
                Connection con = data.getConnectData();
                String C_Email=customertv.getSelectionModel().getSelectedItem().getEmail();
                String str="DELETE FROM USER_TABLE WHERE EMAIL_USER='"+C_Email+"'";
                Statement stmt = con.createStatement();
                isDeleted(stmt.executeUpdate(str));
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Exception in Customer delete");
            }

            customertv.getItems().removeAll(customertv.getSelectionModel().getSelectedItem());
        }
    }

    public void btnaddclicked(ActionEvent actionEvent) {
        if (!producttv.isVisible()) {
            if(workertv.isVisible())
            {
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("Business-view/WorkerAdd.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) tfsearch1.getScene().getWindow();
                    stage.setScene(scene);
                }
                catch (IOException e)
                {
                    LOGGER.log(Level.WARNING, "Exception in Worker add button");
                }
            }
            else if(customertv.isVisible())
            {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Sign-Up.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) tfsearch1.getScene().getWindow();
                    stage.setScene(scene);
                }
                catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Exception in Customer add button");
                }
            }
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Product-view/Product-entry-view.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) tfsearch1.getScene().getWindow();
                stage.setScene(scene);
            }
            catch (IOException e) {
                LOGGER.log(Level.WARNING, "Exception in Product add button");
            }
        }
    }





    public void BTN_LOGOUT_B(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Reports.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception in Logout2 Clicked");
        }
    }

    public void btnchartclicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Chart.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception in chart Clicked");
        }
    }
}
