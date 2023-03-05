module com.example.softwareproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example to javafx.fxml;
    exports com.example;
}