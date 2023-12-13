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

    // Etiquetas en la interfaz de usuario
    @FXML
    public Label SaludoUsuario;
    @FXML
    public Label Fecha;

    // Instancia del gestor de usuarios
    private ControladorIntermedio.GestorUsuarios gu;

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
        this.gu = gu;
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
     * Actualiza el saludo en la interfaz de usuario.
     *
     * @param nuevoSaludo Nuevo saludo.
     */
    public void actualizarSaludo(String nuevoSaludo) {
        nuevoSaludo = "Ejemplo";
        SaludoUsuario.setText(nuevoSaludo);
    }

    /**
     * Actualiza las etiquetas de fecha y usuario en la interfaz de usuario.
     *
     * @param usuario        Usuario actual.
     * @param SaludoUsuario  Etiqueta de saludo en la interfaz de usuario.
     * @param Fecha          Etiqueta de fecha en la interfaz de usuario.
     */
    public static void actualizarFechayUsuario(ControladorIntermedio.Usuario usuario, Label SaludoUsuario, Label Fecha) {
        if (SaludoUsuario != null && Fecha != null) {
            SaludoUsuario.setText("Saludos " + usuario.getNombreUsuario() + ".");

            LocalDateTime fechaIngreso = usuario.getFechaIngresoApp();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fechaFormada = fechaIngreso.format(formatter);
            Fecha.setText(fechaFormada);

        } else {
            System.err.println("Error: SaludoUsuario or Fecha are null");
        }
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
