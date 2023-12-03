package com.example.appbanco.controladores;

import com.example.appbanco.excepciones.NoRegistroException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorLogin extends Application {
    private final ControladorIntermedio.GestorUsuarios gestorUsuarios = new ControladorIntermedio.GestorUsuarios();
    @FXML
    private TextField mombreUsuarioF;
    @FXML
    private PasswordField claveUsuarioF;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private void registrarUsuario(MouseEvent event){
        String nombreUsuario = mombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();
        try {
            if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()){
                gestorUsuarios.registrarUsuario(nombreUsuario, claveUsuario);
                // Mostrar mensaje de registro exitoso si es necesario.
                mostrarAlerta("Registro Exitoso", "Usuario registrado correctamente.");
            } else {
                // Muestra mensaje de error si no se puede registrar al usuario.
                throw new NoRegistroException("Nombre de usuario y contraseña no pueden estar vacíos.");
            }
        } catch (NoRegistroException e) {
            // Manejar la excepción específica para la falta de registro.
            mostrarAlerta("Error de Registro", e.getMessage());
        }
    }

    @FXML
    private void login(MouseEvent event) {
        String nombreUsuario = mombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();
        try {
            if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()) {
                if (gestorUsuarios.verificarLogin(nombreUsuario, claveUsuario)) {
                    // Login exitoso, redirigir a la siguiente pantalla.
                } else {
                    throw new NoRegistroException("Nombre de usuario o contraseña incorrectos.");
                }
            } else {
                throw new NoRegistroException("Nombre de usuario y contraseña no pueden estar vacíos.");
            }
        } catch (NoRegistroException e) {
            // Manejar la excepción específica para el error de login.
            mostrarAlerta("Error de Login", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControladorLogin.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
