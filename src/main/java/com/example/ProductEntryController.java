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
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Pants","Shirt","Jacket","Others");
        ComboBox_Clothes.setItems(list);




    }

    public void LogoutBtnClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)mouseEvent.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Exception in Logout Clicked");
        }
    }

    public void BTN_EntercClicked(MouseEvent mouseEvent) {
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
        try {
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();
            String name=TF_Pname.getText(),area=TF_Parea.getText(),quantity=TF_Pquantity.getText(),
                    address=TF_Paddress.getText(),phone=TF_PphoneNumber.getText();
            String useFlag=null,clothType=null,WellCleaned=null,Customer_email="rasheed@gmail.com";
            if(Check_treatment.isSelected())
                WellCleaned="true";
            else WellCleaned="false";
            if(Check_Use.isSelected()){ useFlag="true";clothType=ComboBox_Clothes.getSelectionModel().getSelectedItem().toString(); }
            else {
                useFlag="false";
                clothType=null;
            }
            String all="INSERT INTO Product values(Prouct_ID_sequence.NEXTVAL," + "'" +name+ "'," +"'" +area+ "',"+"'" +quantity+ "',"
                    +"'"+address+"',"+"'"+phone+"',"+"'"+useFlag+"'," +"'"+clothType+"',"+"'"+WellCleaned+"',"+"'"+Customer_email+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);


            TF_Parea.setText("");
            TF_Pname.setText("");
            TF_Pquantity.setText("");
            Check_Use.setSelected(false);
            Check_treatment.setSelected(false);
            ComboBox_Clothes.setVisible(false);

        }
        catch (Exception e)
        {
            System.out.println(e);
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
