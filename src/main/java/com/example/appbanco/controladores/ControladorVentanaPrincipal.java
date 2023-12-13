package com.example.appbanco.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controlador para la ventana principal después del inicio de sesión.
 */
public class ControladorVentanaPrincipal {

    // Instancia de la ventana y del controlador de inicio de sesión
    private Stage stage;
    private ControladorLogin controladorLogin;

    /**
     * Establece la instancia de la ventana principal.
     *
     * @param primaryStage Instancia de la ventana principal.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Establece el gestor de usuarios.
     *
     * @param gu Gestor de usuarios.
     */
    public void setGu(ControladorIntermedio.GestorUsuarios gu) {
        // Instancia del gestor de usuarios
    }

    /**
     * Inicializa el controlador de la ventana principal.
     *
     * @param stage           Instancia de la ventana.
     * @param controladorLogin Instancia del controlador de inicio de sesión.
     */
    public void iniciar(Stage stage, ControladorLogin controladorLogin) {
        this.controladorLogin = controladorLogin;
        this.stage = stage;
    }

    /**
     * Cierra la sesión y vuelve a la ventana de inicio de sesión.
     *
     * @param event Evento de acción.
     */
    @FXML
    public void cerrarSesion(ActionEvent event) {
        controladorLogin.show();
        stage.close();
    }
}
