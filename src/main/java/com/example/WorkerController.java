package com.example;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerController {
    private static final Logger loggER = Logger.getLogger(WorkerController.class.getName());
    @FXML
    private TextField tfpn1;
    @FXML
    private TextField tfid1;
    @FXML
    private TextField tfname;
    private ConnectionDatabase data;
    public void workerinsert(ActionEvent actionEvent) {
        try{
            data=ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String str="INSERT INTO WORKERS VALUES('"+tfid1.getText()+"',"+"'"+tfname.getText()+"',"+"'"+tfpn1.getText()+"',"+"'"+"false')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);
            tfpn1.setText("");
            tfname.setText("");
            tfid1.setText("");
        }
        catch (Exception e)
        {
            loggER.log(Level.WARNING,"Exception in Insert");
        }

    }

    public void workerBackClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Business-view/Business.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            loggER.log(Level.WARNING,"Exception in Logout2 Clicked");
        }
    }
}
