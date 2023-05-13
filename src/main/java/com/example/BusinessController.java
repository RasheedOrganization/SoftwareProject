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
    private TableView<Customer> Customer_TV;

    @FXML
    private TableView<Product> Product_TV;
    @FXML
    private TableView<Worker> Worker_TV;

    @FXML
    private TextField TF_search;

    @FXML
    private TableColumn<Customer, String> TV_CUSTOMR_Email;

    @FXML
    private TableColumn<Customer, String> TV_CUSTOMR_NAME;

    @FXML
    private TableColumn<Customer, String> TV_CUSTOMR_Phone;

    @FXML
    private TableColumn<Product, String> TV_P_ID;

    @FXML
    private TableColumn<Product, String> TV_P_address;

    @FXML
    private TableColumn<Product, String> TV_P_area;

    @FXML
    private TableColumn<Product, String> TV_P_date;

    @FXML
    private TableColumn<Product, String> TV_P_name;

    @FXML
    private TableColumn<Product, Float> TV_P_price;

    @FXML
    private TableColumn<Product, String> TV_P_quantity;

    @FXML
    private TableColumn<Product, String> TV_P_status;

    @FXML
    private TableColumn<Worker, String> TV_W_ID;

    @FXML
    private TableColumn<Worker, String> TV_W_flag;

    @FXML
    private TableColumn<Worker, String> TV_W_name;

    @FXML
    private TableColumn<Worker, String> TV_W_phone;


    @FXML
    static ObservableList<Customer> C_LIST = FXCollections.observableArrayList();
    @FXML
    static ObservableList<Product> P_LIST = FXCollections.observableArrayList();
    @FXML
    static ObservableList<Worker> W_LIST = FXCollections.observableArrayList();




    private ConnectionDatabase Data;

    public void initialize(URL url, ResourceBundle resourceBundle){
        TV_CUSTOMR_NAME.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TV_CUSTOMR_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        TV_CUSTOMR_Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));




        TV_P_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TV_P_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TV_P_area.setCellValueFactory(new PropertyValueFactory<>("Area"));
        TV_P_quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        TV_P_address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        TV_P_status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        TV_P_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        TV_P_price.setCellValueFactory(new PropertyValueFactory<>("Price"));



        TV_W_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TV_W_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TV_W_phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        TV_W_flag.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        CustomerHelper();
        Customer_TV.setVisible(true);
        Product_TV.setVisible(false);
        Worker_TV.setVisible(false);









    }

    private void WorkerSearch() {
        FilteredList<Worker>filter=new FilteredList<>(W_LIST, b ->true);
        TF_search.textProperty().addListener((observable,OldVal,NewVal)-> {
            filter.setPredicate(worker->{
                if(NewVal.isEmpty() || NewVal.isBlank() || NewVal==null)
                {
                    return true;
                }
                String searchKey=NewVal.toLowerCase();
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
        sortedData.comparatorProperty().bind(Worker_TV.comparatorProperty());
        Worker_TV.setItems(sortedData);


    }
    private void ProductSearch() {
        FilteredList<Product>filter=new FilteredList<>(P_LIST, b ->true);
        TF_search.textProperty().addListener((observable,OldVal,NewVal)-> {
            filter.setPredicate(product->{
                if(NewVal.isEmpty() || NewVal.isBlank() || NewVal==null)
                {
                    return true;
                }
                String searchKey=NewVal.toLowerCase();
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
        sortedData.comparatorProperty().bind(Product_TV.comparatorProperty());
        Product_TV.setItems(sortedData);
    }
    private void CustomerSearch() {
        FilteredList<Customer>filter=new FilteredList<>(C_LIST, b ->true);
        TF_search.textProperty().addListener((observable,OldVal,NewVal)-> {
            filter.setPredicate(customer->{
                if(NewVal.isEmpty() || NewVal.isBlank())
                {
                    return true;
                }
                String searchKey=NewVal.toLowerCase();
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
        sortedData.comparatorProperty().bind(Customer_TV.comparatorProperty());
        Customer_TV.setItems(sortedData);
    }


    private void CustomerHelper() {
        C_LIST.clear();
        try {
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

            String str="SELECT NAME_USER,EMAIL_USER,PHONE_USER from USER_TABLE";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                C_LIST.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3)));
            }

            Customer_TV.setItems(C_LIST);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Customer Table");
        }
        Customer_TV.setVisible(true);
        Product_TV.setVisible(false);
        Worker_TV.setVisible(false);
        CustomerSearch();
    }
    private void ProductHelper() {
        P_LIST.clear();
        try {
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

            String str="SELECT PRODUCTID,PRICE,PRODUCTNAME,PRODUCTAREA,QUANTITY,ADDRESS,STATUS,STRINGDATE from PRODUCT";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                double f=Math.round(rs.getDouble(2) * 1000) / 1000.0;
                P_LIST.add(new Product
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

            Product_TV.setItems(P_LIST);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Product Table");
        }
        Customer_TV.setVisible(false);
        Product_TV.setVisible(true);
        Worker_TV.setVisible(false);
        ProductSearch();
    }
    private void WorkerHelper() {
        W_LIST.clear();
        try {
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

            String str="SELECT ID,NAME,PHONENUMBER,WORKFLAG from WORKERS";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                W_LIST.add(new Worker(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }

            Worker_TV.setItems(W_LIST);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "Exception in Worker Table");
        }
        Customer_TV.setVisible(false);
        Product_TV.setVisible(false);
        Worker_TV.setVisible(true);
        WorkerSearch();
    }


    public void BTN_P_CLICKED(ActionEvent actionEvent) {
        ProductHelper();
    }

    public void BTN_C_Clicked(ActionEvent actionEvent) {
        CustomerHelper();
    }

    public void BTN_W_CLICKED(ActionEvent actionEvent) {
        WorkerHelper();
    }

    public void isDeleted(int deleted) {
        if(deleted > 0)
            JOptionPane.showMessageDialog(null , DELETEDSUCCESSFULLY);
        else
            JOptionPane.showMessageDialog(null , NOTHINGDELETED);
    }

    public void BTN_Delete_Clicked(ActionEvent actionEvent)
    {
        if(Product_TV.isVisible())
        {
                try{
                    Data=ConnectionDatabase.getInstance();
                    Connection con = Data.getConnectData();
                    String P_ID=Product_TV.getSelectionModel().getSelectedItem().getID();
                    String str="DELETE FROM Product WHERE PRODUCTID='"+P_ID+"'";
                    Statement stmt = con.createStatement();
                    isDeleted(stmt.executeUpdate(str));
                }
                catch (SQLException e)
                {
                    LOGGER.log(Level.WARNING, "Exception in Product delete");
                }

                ProductEntryController.StatusHelper();

                Product_TV.getItems().removeAll(Product_TV.getSelectionModel().getSelectedItem());

        }
        else if(Worker_TV.isVisible())
        {
            try{
                Data=ConnectionDatabase.getInstance();
                Connection con = Data.getConnectData();
                String W_ID=Worker_TV.getSelectionModel().getSelectedItem().getID();
                String str="DELETE FROM WORKERS WHERE ID='"+W_ID+"'";
                Statement stmt = con.createStatement();
                isDeleted(stmt.executeUpdate(str));
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Exception in Worker delete");
            }

            Worker_TV.getItems().removeAll(Worker_TV.getSelectionModel().getSelectedItem());
        }
        else if(Customer_TV.isVisible())
        {
            try{
                Data=ConnectionDatabase.getInstance();
                Connection con = Data.getConnectData();
                String C_Email=Customer_TV.getSelectionModel().getSelectedItem().getEmail();
                String str="DELETE FROM USER_TABLE WHERE EMAIL_USER='"+C_Email+"'";
                Statement stmt = con.createStatement();
                isDeleted(stmt.executeUpdate(str));
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Exception in Customer delete");
            }

            Customer_TV.getItems().removeAll(Customer_TV.getSelectionModel().getSelectedItem());
        }
    }

    public void BTN_ADD_Clicked(ActionEvent actionEvent) {
        if (!Product_TV.isVisible()) {
            if(Worker_TV.isVisible())
            {
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("Business-view/WorkerAdd.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) TF_search.getScene().getWindow();
                    stage.setScene(scene);
                }
                catch (IOException e)
                {
                    LOGGER.log(Level.WARNING, "Exception in Worker add button");
                }
            }
            else if(Customer_TV.isVisible())
            {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Sign-Up.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) TF_search.getScene().getWindow();
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
                Stage stage = (Stage) TF_search.getScene().getWindow();
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

    public void BTN_chart_Clicked(ActionEvent event) {
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
