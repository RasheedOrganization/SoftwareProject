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
    private TextField tfpn1;
    @FXML
    private TextField tfid1;
    @FXML
    private TextField tfname;
    private ConnectionDatabase Data;
    public void workerinsert(ActionEvent actionEvent) {
        try{
            Data=ConnectionDatabase.getInstance();
            Connection con = Data.getConnectData();

            String str="INSERT INTO WORKERS VALUES('"+tfid1.getText()+"',"+"'"+tfname.getText()+"',"+"'"+tfpn1.getText()+"',"+"'"+"false')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);
            tfpn1.setText("");
            tfname.setText("");
            tfid1.setText("");
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
