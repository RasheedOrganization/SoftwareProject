package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    private static final Logger loggER = Logger.getLogger(HelloController.class.getName());

    @FXML
    private Label createanaccount;

    @FXML
    private PasswordField passwordtext;

    @FXML
    private TextField usernametext;
    private static String gmailcounter;
    private static String userNamee;

    private final ConnectionDatabase data = ConnectionDatabase.getInstance();

    static String getGmailCounter() {
        return gmailcounter;
    }
    static String getUserNamee() {
        return userNamee;
    }
    static void setGmailCounter(String gmail){
        gmailcounter = gmail;
    }
    static void setUserNamee(String namee){
        userNamee = namee;
    }
    @FXML
    void createAnAccountClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Sign-Up.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) createanaccount.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e) {
            loggER.log(Level.WARNING, "Exception");
        }
    }

    @FXML
    void forgotClicked(MouseEvent event) {
            JOptionPane.showMessageDialog(null,"احلف انك ناسي ... على كلن فش تفوت على حسابك روح على مغسلة تانية");
    }

    @FXML
    void signInClicked(ActionEvent event) {
        try {
            Connection con = data.getConnectData();
            String all = "select email_user, password,NAME_USER,ADMIN_FLAG from user_table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            String string = usernametext.getText();
            while (rs.next()) {
                String emailL = rs.getString(1);
                if(emailL.equals(string)) {
                    String password = rs.getString(2);
                    if(password.equals(passwordtext.getText())) {
                        String whereToGo;
                        setGmailCounter(rs.getString(1));
                        setUserNamee(rs.getString(3));
                        if(rs.getString(4).equals("true"))
                            whereToGo="Sign-up-view/Reports.fxml";
                        else whereToGo="Product-view/Product-entry-view.fxml";
                        Parent root = FXMLLoader.load(getClass().getResource(whereToGo));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) usernametext.getScene().getWindow();
                        stage.setScene(scene);
                        return;
                    }
                    JOptionPane.showMessageDialog(null,"Wrong password");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Wrong emailL");
        } catch (SQLException | IOException e) {
            loggER.log(Level.WARNING, "Exception");
        }
    }
}
