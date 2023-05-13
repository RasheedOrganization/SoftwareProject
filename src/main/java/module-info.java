module com.example.softwareproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.kordamp.bootstrapfx.core;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires java.desktop;
    requires AnimateFX;
    requires jasperreports;
    requires java.mail;


    opens com.example to javafx.fxml;
    exports com.example;
}