package com.example.appbanco.controladores;

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

/**
 * Controlador para la ventana de inicio de sesión.
 */
public class ControladorLogin extends Application {
    public void setStage(Stage primarystage) {
        stage = primarystage;
    }
    private final ControladorIntermedio.GestorUsuarios gestorUsuarios = new ControladorIntermedio.GestorUsuarios();

    // Elementos de la interfaz de usuario
    @FXML
    private TextField nombreUsuarioF;
    @FXML
    private PasswordField claveUsuarioF;
    @FXML
    private Label SaludoUsuario;
    @FXML
    private Label Fecha;

    // Alerta para mostrar mensajes de error
    Alert alert = new Alert(Alert.AlertType.ERROR);

    // Instancia de la ventana
    private Stage stage;

    // Inicialización de la interfaz de usuario
    @FXML
    private void initialize() {
        SaludoUsuario = new Label();
        Fecha = new Label();
    }

    /**
     * Método para registrar un nuevo usuario.
     */
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

    /**
     * Método para realizar el inicio de sesión.
     */
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

    /**
     * Muestra una alerta con el título y mensaje especificados.
     *
     * @param titulo  Título de la alerta.
     * @param mensaje Mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Redirige a la ventana principal después de un inicio de sesión exitoso.
     */
    private void redirigirAVentanaPrincipal() {
        try {
            // Carga el archivo FXML de la ventana principal
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/appbanco/FXML/PaginaPrincipal.fxml"));
            Parent root = fxmlLoader.load();

            // Verifica si se cargó correctamente el controlador
            if (fxmlLoader.getController() == null) {
                System.err.println("Error al cargar el controlador");
                return;
            }

            // Verifica si la instancia de Stage es nula
            if (stage == null) {
                System.err.println("Error: la instancia de Stage es nula");
                return;
            }

            // Obtiene el controlador de la ventana principal
            ControladorVentanaPrincipal principal = fxmlLoader.getController();

            // Configura la escena y la muestra en una nueva instancia de Stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            principal.iniciar(stage, this); // Inicia la ventana principal con el nuevo Stage
            stage.show();

            // Cierra la ventana de inicio de sesión actual
            this.stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia la aplicación con la ventana de inicio de sesión.
     *
     * @param stage Stage principal de la aplicación.
     * @throws Exception Excepción en caso de error al cargar el FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Carga el archivo FXML de la ventana de inicio de sesión
        FXMLLoader loader = new FXMLLoader(ControladorLogin.class.getResource("/com/example/appbanco/FXML/Login.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 700);

        // Obtiene el controlador de la ventana de inicio de sesión
        ControladorLogin controladorLogin = loader.getController();
        controladorLogin.initialize();

        // Configura el título y la escena del Stage principal
        stage.setTitle("Login");
        stage.setScene(scene);

        // Muestra el Stage principal
        stage.show();
    }

    /**
     * Muestra la ventana de inicio de sesión.
     */
    public void show() {
        stage.show();
    }
}
