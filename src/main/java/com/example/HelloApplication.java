package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {
    //SignUpSteps S;
    @Override
    public void start(Stage stage) throws IOException, MessagingException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sign-up-view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}