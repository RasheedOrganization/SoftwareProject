package com.example;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label CreateAnAccount;

    @FXML
    private Label Forgot;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private Button SignInButton;

    @FXML
    private TextField UserNameText;


    private ConnectionDatabase Data = ConnectionDatabase.getInstance();

    @FXML
    void CreateAnAccountClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Sign-Up.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) CreateAnAccount.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e) {
            System.out.println("Exception in CreateAnAccountClicked");
        }
    }

    @FXML
    void ForgotClicked(MouseEvent event) {

    }

    @FXML
    void SignInClicked(ActionEvent event) {
        try {
            Connection con = Data.getConnectData();
            String all = "select email_user, password from user_table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            String string = UserNameText.getText();
            while (rs.next()) {
                String Email = rs.getString(1);
                if(Email.equals(string)) {
                    String Password = rs.getString(2);
                    if(Password.equals(PasswordText.getText())) {
                        Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Reports.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) UserNameText.getScene().getWindow();
                        stage.setScene(scene);
                        flag = false;
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Wrong Password");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null,"Wrong Email");
            }
        } catch (Exception e) {
            System.out.println("Exception in SignInClicked");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
