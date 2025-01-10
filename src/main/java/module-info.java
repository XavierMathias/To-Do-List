module com.example.crud {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.crud to javafx.fxml;  // Adjusted for HelloController
    exports com.example.crud;
}
