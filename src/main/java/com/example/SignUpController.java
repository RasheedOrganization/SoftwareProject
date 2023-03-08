package com.example;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private PasswordField ConfirmPasswordText;

    @FXML
    private TextField EmailText;

    @FXML
    private Label Login;

    @FXML
    private TextField NameText;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private TextField PhoneText;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField UserNameText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
