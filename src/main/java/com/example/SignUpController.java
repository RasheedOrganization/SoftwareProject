package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import animatefx.animation.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private DatePicker Birthdate;
    @FXML
    private PasswordField ConfirmPasswordText;

    @FXML
    private TextField EmailText;

    @FXML
    private Label Login;

    @FXML
    private TextField NameText;

    @FXML
    private PasswordField passwordtext;

    @FXML
    private TextField PhoneText;

    @FXML
    private Button SignUpButton;

    ConnectionDatabase data = ConnectionDatabase.getInstance();

    @FXML
    void LoginClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e) {
            System.out.println("Exception in LoginClicked");
        }
    }

    @FXML
    void SignUpClicked(ActionEvent event) {
        if(EmailText.getText().isBlank() || NameText.getText().isBlank() || PhoneText.getText().isBlank() || passwordtext.getText().isBlank() || ConfirmPasswordText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null,"Field is Empty");
            return;
        }
        String Email = EmailText.getText();
        String Name = NameText.getText();
        String Phone = PhoneText.getText();
        String Pass = passwordtext.getText();
        String PassCheck = ConfirmPasswordText.getText();
        if(!Validation.emailPatternMatches(Email)) {
            JOptionPane.showMessageDialog(null,"Email is not Validation");
        }
        else if(!Validation.phonePatternMatches(Phone)) {
            JOptionPane.showMessageDialog(null,"Phone is not Validation");
        }
        else if(!Validation.passwordPatternMatches(Pass)) {
            JOptionPane.showMessageDialog(null,"Password is not Validation");
        }
        else if (!Pass.equals(PassCheck)){
            JOptionPane.showMessageDialog(null,"Password not matches");
        }
        else {
            try {
                Connection con = data.getConnectData();
                String all = "select email_user from user_table";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(all);
                while (rs.next()) {
                    String temp = rs.getString(1);
                    if (temp.equals(Email)) {
                        JOptionPane.showMessageDialog(null, "Email Duplicate");
                        return;
                    }
                }
                LocalDate date = Birthdate.getValue();
                String dateFormat = date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                all = "insert into user_table values ('" + Name + "',"+"'" + Email + "',"+"'" + Pass + "','"+ dateFormat +"',"+"'false',"+"'"+Phone+"')";
                stmt.executeUpdate(all);
                //con.commit();
                //con.close();
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Reports.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.setScene(scene);
            }
            catch (Exception e)
            {
               throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate local = LocalDate.of(2003,3,28);
        Birthdate.setValue(local);
    }
}
