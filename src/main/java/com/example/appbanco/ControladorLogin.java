package com.example.appbanco;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ControladorLogin extends Application {
    private final ControladorIntermedio.GestorUsuarios gestorUsuarios = new ControladorIntermedio.GestorUsuarios();
    @FXML
    private TextField mombreUsuarioF;
    @FXML
    private PasswordField claveUsuarioF;

    @FXML
    private void registrarUsuario(ActionEvent event){
        String nombreUsuario = mombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();

        if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()){
                gestorUsuarios.registrarUsuario(nombreUsuario, claveUsuario);
        } else {
            //Mensaje de Error POPUP
        }
    }

    @FXML
    private void login(ActionEvent event) {
        String nombreUsuario = mombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();
        if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty()) {
            if (gestorUsuarios.verificarLogin(nombreUsuario, claveUsuario)) {
                // Login exitoso, redirigir a la siguiente pantalla.
            } else {
                // Muestra un mensaje de error si el login no es válido Por ventana POPUP
            }
        } else {
            // Muestra un mensaje de error POPUP
        }
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