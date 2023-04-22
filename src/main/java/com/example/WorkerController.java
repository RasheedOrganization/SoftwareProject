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

public class WorkerController {
    @FXML
    private TextField TF_PN;
    @FXML
    private TextField TF_ID;
    @FXML
    private TextField TF_NAME;
    public void Worker_insert(ActionEvent actionEvent) {


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
