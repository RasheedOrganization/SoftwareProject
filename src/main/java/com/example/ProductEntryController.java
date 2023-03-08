package com.example;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class ProductEntryController {
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
    ImageView BTN_AddProduct;
    @FXML
    ImageView BTN_logout;
    @FXML
    Button BTN_Entry;

    public void LogoutBtnClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)mouseEvent.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BTN_EntercClicked(MouseEvent mouseEvent) {
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
        try {
            ConnectionDatabase.getInstance();

            Connection con = data.getConnectData();
            String str = "select * from Product";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);

        }
        catch (Exception e)
        {

        }
    }
}
