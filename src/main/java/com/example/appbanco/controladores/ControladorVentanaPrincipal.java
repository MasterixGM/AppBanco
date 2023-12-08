package com.example.appbanco.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {
    public Label SaludoUsuario;
    public Label Fecha;
    private ControladorIntermedio.GestorUsuarios gu;
    private Stage stage;
    private ControladorLogin controladorLogin;

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public void setGu(ControladorIntermedio.GestorUsuarios gu){
        this.gu = gu;
    }

    public void iniciar(Stage stage, ControladorLogin controladorLogin) {
        this.controladorLogin = controladorLogin;
        this.stage = stage;
    }
    public void cerrarSesion(ActionEvent event){
        controladorLogin.show();
        stage.close();
    }
    // Otro código del controlador...
}
