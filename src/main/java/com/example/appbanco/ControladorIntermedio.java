package com.example.appbanco;

import java.util.ArrayList;
import java.util.List;

public class ControladorIntermedio {
    public static class Usuario {
        private final String nombreUsuario;
        private final String clave;

        public Usuario(String nombreUsuario, String clave) {
            this.nombreUsuario = nombreUsuario;
            this.clave = clave;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public String getClave() {
            return clave;
        }
    }
    public static class GestorUsuarios {
        private final List<Usuario> usuariosRegistrados;

        public GestorUsuarios(){
            usuariosRegistrados = new ArrayList<>();
        }

        public void registrarUsuario(String nombreUsuario, String clave) {
            Usuario nuevoUsuario = new Usuario(nombreUsuario, clave);
            usuariosRegistrados.add(nuevoUsuario);
        }
        public boolean verificarLogin(String nombreUsuario, String clave){
            for (Usuario usuario : usuariosRegistrados) {
                if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave)){
                    return true;
                }
            }
            return false;
        }
    }

}
