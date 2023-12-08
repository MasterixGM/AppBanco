module com.example.appbanco {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.appbanco.Controladores;
    opens com.example.appbanco.Controladores to javafx.fxml;
}