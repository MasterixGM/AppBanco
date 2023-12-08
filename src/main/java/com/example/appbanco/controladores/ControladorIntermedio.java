package com.example.appbanco.Controladores;
import com.example.appbanco.excepciones.NoRegistroException;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorIntermedio {
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

        public LocalDateTime getFechaIngresoApp(){
            return fechaIngresoApp;
        }
    }

    public static class GestorUsuarios {
        private final List<Usuario> usuariosRegistrados;

        public GestorUsuarios(){
            usuariosRegistrados = new ArrayList<>();
        }

        public void registrarUsuario(String nombreUsuario, String clave, Label SaludoUsuario, Label Fecha) throws NoRegistroException {
            if (usuariosRegistrados.stream().anyMatch(usuario -> usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave))){
                throw new NoRegistroException("El usuario ya está registrado");
            } else {
                Usuario nuevoUsuario = new Usuario(nombreUsuario, clave);
                usuariosRegistrados.add(nuevoUsuario);
                actualizarFechayUsuario(nuevoUsuario, SaludoUsuario, Fecha);
            }
        }

        public boolean verificarLogin(String nombreUsuario, String clave, Label SaludoUsuario, Label Fecha){
            for (Usuario usuario : usuariosRegistrados) {
                if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave)){
                    actualizarFechayUsuario(usuario, SaludoUsuario, Fecha);
                    return true;
                }
            }
            return false;
        }

        public void actualizarFechayUsuario(Usuario usuario, Label SaludoUsuario, Label Fecha) {
            // Logica para actualizar al label de fecha y el label de saludo de usuario en mi página principal
            if (SaludoUsuario != null && Fecha != null) {
                SaludoUsuario.setText("Saludos " + usuario.getNombreUsuario() + ".");

                LocalDateTime fechaIngreso = usuario.getFechaIngresoApp();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String fechaFormada = fechaIngreso.format(formatter);
                Fecha.setText(fechaFormada);
            } else {
                System.err.println("Error: SaludoUsuario o Fecha son nulos");
            }
        }
    }
}
