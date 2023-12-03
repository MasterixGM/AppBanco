module com.example.appbanco {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.appbanco to javafx.fxml;
    exports com.example.appbanco;
}