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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    private static final Logger LOGGER = Logger.getLogger(Chart.class.getName());

    @FXML
    private Label CreateAnAccount;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private TextField UserNameText;
    private static String GmailCounter;
    private static String UserNamee;

    private ConnectionDatabase Data = ConnectionDatabase.getInstance();

    String getGmailCounter() {
        return GmailCounter;
    }
    String getUserNamee() {
        return UserNamee;
    }
    @FXML
    void CreateAnAccountClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/Sign-Up.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) CreateAnAccount.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception");
        }
    }

    @FXML
    void ForgotClicked(MouseEvent event) {
            JOptionPane.showMessageDialog(null,"احلف انك ناسي ... على كلن فش تفوت على حسابك روح على مغسلة تانية");
    }

    @FXML
    void SignInClicked(ActionEvent event) {
        try {
            Connection con = Data.getConnectData();
            String all = "select email_user, password,NAME_USER,ADMIN_FLAG from user_table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            String string = UserNameText.getText();
            while (rs.next()) {
                String Email = rs.getString(1);
                if(Email.equals(string)) {
                    String Password = rs.getString(2);
                    if(Password.equals(PasswordText.getText())) {
                        String WhereToGo;
                        GmailCounter=rs.getString(1);
                        UserNamee=rs.getString(3);
                        if(rs.getString(4).equals("true"))
                            WhereToGo="Sign-up-view/Reports.fxml";
                        else WhereToGo="Product-view/Product-entry-view.fxml";
                        Parent root = FXMLLoader.load(getClass().getResource(WhereToGo));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) UserNameText.getScene().getWindow();
                        stage.setScene(scene);
                        return;
                    }
                    JOptionPane.showMessageDialog(null,"Wrong Password");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Wrong Email");
        } catch (SQLException | IOException e) {
            LOGGER.log(Level.WARNING, "Exception");
        }
    }
}
