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

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController implements Initializable {
    private static final Logger loggER = Logger.getLogger(SignUpController.class.getName());
    @FXML
    private DatePicker bBirthdate;
    @FXML
    private PasswordField cConfirmPasswordText;

    @FXML
    private TextField eEmailText;

    @FXML
    private Label lLogin;

    @FXML
    private TextField nNameText;

    @FXML
    private PasswordField passwordtext;

    @FXML
    private TextField pPhoneText;

    @FXML
    private Button sSignUpButton;

    connectionDatabase data = connectionDatabase.getInstance();

    @FXML
    void lLoginClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) lLogin.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e) {
            loggER.log(Level.WARNING,"Exception in lLoginClicked");
        }
    }

    @FXML
    void sSignUpClicked(ActionEvent event) {
        if(eEmailText.getText().isBlank() || nNameText.getText().isBlank() || pPhoneText.getText().isBlank() || passwordtext.getText().isBlank() || cConfirmPasswordText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null,"Field is Empty");
            return;
        }
        String emailL = eEmailText.getText();
        String name = nNameText.getText();
        String phone = pPhoneText.getText();
        String pass = passwordtext.getText();
        String passCheck = cConfirmPasswordText.getText();
        if(!Validation.emailPatternMatches(emailL)) {
            JOptionPane.showMessageDialog(null,"emailL is not Validation");
        }
        else if(!Validation.phonePatternMatches(phone)) {
            JOptionPane.showMessageDialog(null,"phone is not Validation");
        }
        else if(!Validation.passwordPatternMatches(pass)) {
            JOptionPane.showMessageDialog(null,"password is not Validation");
        }
        else if (!pass.equals(passCheck)){
            JOptionPane.showMessageDialog(null,"password not matches");
        }
        else {
            try {
                Connection con = data.getConnectData();
                String all = "select email_user from user_table";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(all);
                while (rs.next()) {
                    String temp = rs.getString(1);
                    if (temp.equals(emailL)) {
                        JOptionPane.showMessageDialog(null, "emailL Duplicate");
                        return;
                    }
                }
                LocalDate date = bBirthdate.getValue();
                String dateFormat = date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                all = "insert into user_table values ('" + name + "',"+"'" + emailL + "',"+"'" + pass + "','"+ dateFormat +"',"+"'false',"+"'"+phone+"')";
                stmt.executeUpdate(all);

            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Reports.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) lLogin.getScene().getWindow();
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
        bBirthdate.setValue(local);
    }
}
