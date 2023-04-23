package com.example;
import com.example.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class WorkerController {
    @FXML
    private TextField TF_PN;
    @FXML
    private TextField TF_ID;
    @FXML
    private TextField TF_NAME;
    private ConnectionDatabase Data;
    public void Worker_insert(ActionEvent actionEvent) {
        try{
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

            String str="INSERT INTO WORKERS VALUES('"+TF_ID.getText()+"',"+"'"+TF_NAME.getText()+"',"+"'"+TF_PN.getText()+"',"+"'"+"false')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);
            TF_PN.setText("");
            TF_NAME.setText("");
            TF_ID.setText("");
        }
        catch (Exception e)
        {
            System.out.println("Exception in Insert");
        }

    }

    public void WorkerBackClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Exception in Logout2 Clicked");
        }
    }
}
