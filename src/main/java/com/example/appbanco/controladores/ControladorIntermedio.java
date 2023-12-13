package com.example.appbanco.controladores;

import com.example.appbanco.excepciones.NoRegistroException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase mediadora que gestiona los usuarios y su registro.
 */
public class ControladorIntermedio {
    @FXML
    public Label SaludoUsuario;
    @FXML
    public Label Fecha;

    /**
     * Clase interna que representa a un usuario.
     */

    public static class Usuario {
        private final String nombreUsuario;
        private final String clave;
        private final LocalDateTime fechaIngresoApp;

        public Usuario(String nombreUsuario, String clave) {
            this.nombreUsuario = nombreUsuario;
            this.clave = clave;
            this.fechaIngresoApp = LocalDateTime.now();
        }


        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public String getClave() {
            return clave;
        }

        public LocalDateTime getFechaIngresoApp() {
            return fechaIngresoApp;
        }

    }


    /**
     * Clase que gestiona la lista de usuarios registrados.
     */
    public static class GestorUsuarios {
        private final List<Usuario> usuariosRegistrados;

        public GestorUsuarios() {
            usuariosRegistrados = new ArrayList<>();
        }

        /**
         * Registra un nuevo usuario.
         *
         * @param nombreUsuario Nombre del usuario.
         * @param clave         Clave del usuario.
         * @param SaludoUsuario Etiqueta de saludo en la interfaz de usuario.
         * @param Fecha         Etiqueta de fecha en la interfaz de usuario.
         * @throws NoRegistroException Si el usuario ya está registrado.
         */
        public void registrarUsuario(String nombreUsuario, String clave, Label SaludoUsuario, Label Fecha) throws NoRegistroException {
            if (usuariosRegistrados.stream().anyMatch(usuario -> usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave))) {
                throw new NoRegistroException("El usuario '" + nombreUsuario + "' ya está registrado");
            } else {
                Usuario nuevoUsuario = new Usuario(nombreUsuario, clave);
                usuariosRegistrados.add(nuevoUsuario);
                actualizarFechayUsuario(nuevoUsuario, SaludoUsuario, Fecha);
            }
        }

        /**
         * Actualiza las etiquetas de fecha y usuario en la interfaz de usuario.
         *
         * @param usuario        Usuario actual.
         * @param SaludoUsuario  Etiqueta de saludo en la interfaz de usuario.
         * @param Fecha          Etiqueta de fecha en la interfaz de usuario.
         */
        public static void actualizarFechayUsuario(Usuario usuario, Label SaludoUsuario, Label Fecha) {
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
         * Verifica el inicio de sesión del usuario.
         *
         * @param nombreUsuario Nombre del usuario.
         * @param clave         Clave del usuario.
         * @param SaludoUsuario Etiqueta de saludo en la interfaz de usuario.
         * @param Fecha         Etiqueta de fecha en la interfaz de usuario.
         * @return true si el inicio de sesión es exitoso, false de lo contrario.
         */
        public boolean verificarLogin(String nombreUsuario, String clave, Label SaludoUsuario, Label Fecha) {
            for (Usuario usuario : usuariosRegistrados) {
                if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave)) {
                    actualizarFechayUsuario(usuario, SaludoUsuario, Fecha);
                    return true;
                }
            }
            return false;
        }
    }
    public void initialize(){
        ControladorIntermedio.GestorUsuarios.actualizarFechayUsuario(this, SaludoUsuario, Fecha);
    }
}
