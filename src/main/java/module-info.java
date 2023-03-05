module com.example.softwareproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.kordamp.bootstrapfx.core;
    requires com.oracle.database.jdbc;
    requires java.sql;

    opens com.example to javafx.fxml;
    exports com.example;
}