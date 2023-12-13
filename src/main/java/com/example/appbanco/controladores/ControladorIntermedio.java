package com.example.appbanco.controladores;

import com.example.appbanco.excepciones.NoRegistroException;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase mediadora que gestiona los usuarios y su registro.
 */
public class ControladorIntermedio {

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
                ControladorVentanaPrincipal.actualizarFechayUsuario(nuevoUsuario, SaludoUsuario, Fecha);
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
                    ControladorVentanaPrincipal.actualizarFechayUsuario(usuario, SaludoUsuario, Fecha);
                    return true;
                }
            }
            return false;
        }
    }
}
