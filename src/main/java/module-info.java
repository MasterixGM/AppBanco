module com.example.appbanco {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.appbanco.controladores;
    opens com.example.appbanco.controladores to javafx.fxml;
}