package com.example.appbanco.Controladores;

import com.example.appbanco.excepciones.NoLoginException;
import com.example.appbanco.excepciones.NoRegistroException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
public class ControladorLogin extends Application {
    public void setStage(Stage primarystage) {
        stage = primarystage;
    }
    private final ControladorIntermedio.GestorUsuarios gestorUsuarios = new ControladorIntermedio.GestorUsuarios();
    @FXML
    private TextField nombreUsuarioF;
    @FXML
    private PasswordField claveUsuarioF;
    @FXML
    Label SaludoUsuario;
    @FXML
    Label Fecha;

    Alert alert = new Alert(Alert.AlertType.ERROR);
    private Stage stage;
    @FXML
    private void initialize() {
        SaludoUsuario = new Label();
        Fecha = new Label();
    }

    @FXML
    private void registrarUsuario(MouseEvent event) {
        String nombreUsuario = nombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();
        try {
            if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty() && SaludoUsuario != null && Fecha != null) {
                gestorUsuarios.registrarUsuario(nombreUsuario, claveUsuario, SaludoUsuario, Fecha);
                mostrarAlerta("Registro Exitoso", "Usuario registrado Correctamente.");
            } else {
                throw new NoRegistroException("Nombre de Usuario y Contraseña no pueden estar Vacíos");
            }
        } catch (NoRegistroException e) {
            mostrarAlerta("Error Registro", e.getMessage());
        }
    }

    @FXML
    private void login(MouseEvent event) {
        String nombreUsuario = nombreUsuarioF.getText();
        String claveUsuario = claveUsuarioF.getText();
        try {
            if (!nombreUsuario.isEmpty() && !claveUsuario.isEmpty() && SaludoUsuario != null && Fecha != null) {
                if (gestorUsuarios.verificarLogin(nombreUsuario, claveUsuario, SaludoUsuario, Fecha)) {
                    // Login exitoso, redirigir a la siguiente pantalla.
                    redirigirAVentanaPrincipal();
                } else {
                    throw new NoLoginException("Nombre de Usuario o Contraseña Incorrectos");
                }
            } else {
                throw new NoLoginException("Usuario y Contraseña no pueden estar vacíos");
            }
        } catch (NoLoginException e) {
            mostrarAlerta("Error de Login", e.getMessage());
        }
    }

        private void mostrarAlerta(String titulo, String mensaje){
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        }

    private void redirigirAVentanaPrincipal(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/appbanco/FXML/PaginaPrincipal.fxml"));
            Parent root = fxmlLoader.load();

            if (fxmlLoader.getController() == null){
                System.err.println("Error al cargar el controlador");
                return;
            }

            if (stage == null){
                System.err.println("Error la Stage es nula");
                return;
            }
            ControladorVentanaPrincipal principal = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            principal.iniciar(stage, this);
            stage.show();
            this.stage.close();
        } catch (IOException e){
            e.printStackTrace();
        }
}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorLogin.class.getResource("/com/example/appbanco/FXML/Login.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 700);

        ControladorLogin controladorLogin = loader.getController();
        controladorLogin.initialize();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    public void show() {
        stage.show();
    }
}