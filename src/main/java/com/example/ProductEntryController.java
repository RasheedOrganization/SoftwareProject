package com.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;



public class ProductEntryController implements Initializable{
    private ConnectionDatabase data;
    @FXML
    TextField TF_Pname;
    @FXML
    TextField TF_Parea;
    @FXML
    TextField TF_Pquantity;
    @FXML
    TextField TF_Paddress;
    @FXML
    TextField TF_PphoneNumber;
    @FXML
    CheckBox Check_Use;
    @FXML
    CheckBox Check_treatment;
    @FXML
    ImageView BTN_AddProduct;
    @FXML
    ImageView BTN_logout;
    @FXML
    Button BTN_Entry;
    @FXML
    private ComboBox<String> ComboBox_Clothes;

    @FXML
    static ObservableList<Invoice> LIST = FXCollections.observableArrayList();
    static String location;
    static double LocalPrice=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Pants","Shirt","Jacket","Others");
        ComboBox_Clothes.setItems(list);
    }



    public void LogoutBtnClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Reports.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)mouseEvent.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Exception in Logout Clicked");
        }
    }

    public void BTN_EnterClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice-view/invoice.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) TF_PphoneNumber.getScene().getWindow();
            stage.setScene(scene);
                TF_Parea.setText("");
                TF_Pname.setText("");
                TF_Pquantity.setText("");
                TF_Paddress.setText("");
                TF_PphoneNumber.setText("");
                Check_Use.setSelected(false);
                Check_treatment.setSelected(false);
                ComboBox_Clothes.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
        try {
            String   name=TF_Pname.getText()
                    ,area=TF_Parea.getText()
                    ,quantity=TF_Pquantity.getText()
                    ,address=TF_Paddress.getText()
                    ,phone=TF_PphoneNumber.getText();
            location=address;

            boolean flag = true;
            for(int i=0;i<area.length();i++)
            {
                if(!Character.isDigit(area.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Area must contain Digits only");
                    flag=false;
                    break;
                }
            }
            for(int i=0;i<quantity.length();i++)
            {
                if(!Character.isDigit(quantity.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Quantity must contain Digits only");
                    flag=false;
                    break;
                }
            }
            if(quantity.equals(null)){
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Quantity can't be empty");
                flag=false;
            }
            if(area.equals(null)) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Area can't be empty");
                flag=false;
            }
            if(address.equals(null)) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Address can't be empty");
                flag=false;
            }
            if(phone.length()!=12 || (phone.charAt(0)!='9' && phone.charAt(1)!='7')) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Please enter a valid Phone Number");
                flag=false;
            }
            for(int i=0;i<name.length();i++) {
                if(Character.isDigit(name.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Name can't contain digits");
                    flag=false;
                    break;
                }
            }


            if(flag) {
                data = ConnectionDatabase.getInstance();
                Connection con = data.getConnectData();
                String    useFlag = null
                        , clothType = null
                        , WellCleaned = null
                        , Customer_email = HelloController.GmailCounter;

                if (Check_treatment.isSelected())
                    WellCleaned = "true";
                else WellCleaned = "false";
                if (Check_Use.isSelected()) {
                    useFlag = "true";
                    clothType = ComboBox_Clothes.getSelectionModel().getSelectedItem().toString();
                }
                else {
                    useFlag = "false";
                    clothType = null;
                }

                String all = "INSERT INTO Product values(Prouct_ID_sequence.NEXTVAL," + "'" + name + "'," + "'" + area + "'," + "'" + quantity + "',"
                        + "'" + address + "'," + "'" + phone + "'," + "'" + useFlag + "'," + "'" + clothType + "'," + "'" + WellCleaned + "'," + "'" + Customer_email + "')";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(all);
                double price = 0;
                if (Check_Use.isSelected()) {
                    if (ComboBox_Clothes.getValue().equals("Pants")) price = Double.parseDouble(quantity) * 5;
                    else if (ComboBox_Clothes.getValue().equals("Shirt")) price = Double.parseDouble(quantity) * 5;
                    else if (ComboBox_Clothes.getValue().equals("Jacket")) price = Double.parseDouble(quantity) * 10;
                    else price = Double.parseDouble(quantity) * 1;
                } else
                    price = Double.parseDouble(quantity) * Double.parseDouble(area) * 0.45;

                if(Check_treatment.isSelected())
                {
                    price+=price*0.05;
                }

                LocalPrice += price;

                LIST.add(
                        new Invoice(name, Double.parseDouble(area), Double.parseDouble(quantity), price)
                );

                TF_Parea.setText("");
                TF_Pname.setText("");
                TF_Pquantity.setText("");
                Check_Use.setSelected(false);
                Check_treatment.setSelected(false);
                ComboBox_Clothes.setVisible(false);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void Check_Use_Clicked(ActionEvent actionEvent) {
        if(Check_Use.isSelected())
        {
            ComboBox_Clothes.setVisible(true);
        }
        else
        {
            ComboBox_Clothes.setVisible(false);
        }
    }

}
