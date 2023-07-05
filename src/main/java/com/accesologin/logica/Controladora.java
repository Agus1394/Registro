package com.accesologin.logica;

import com.accesologin.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public Usuario validar(String usuario, String contrasenia) {

        Usuario user = null;
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        for (Usuario usuarios : listaUsuarios) {
            if (usuarios.getNombre().equals(usuario)) {
                if (usuarios.getContrasenia().equals(contrasenia)) {
                    user = usuarios;
                    return user;
                } else {
                    user = null;
                    return user;
                }
            } else {
                user = null;
            }
        }

        return user;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    public void crearUsuario(String usu, String claveUsuario, String rolRecibido) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usu);
        usuario.setContrasenia(claveUsuario);

        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);

        if (rolEncontrado != null) {
            usuario.setRol(rolEncontrado);
        }

        int id = buscarUltimoIdUsuario();
        usuario.setId(id + 1);

        controlPersis.crearUsuario(usuario);
    }

    private Rol traerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRoles();

        for (Rol rol : listaRoles) {
            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }
        }
        return null;
    }

    private int buscarUltimoIdUsuario() {
        List<Usuario> listaUsuario = this.traerUsuarios();

        //trae la última posición de la listaUsuarios
        Usuario usuario = listaUsuario.get(listaUsuario.size() - 1);
        //retorna la misma Id de la misma posición 
        return usuario.getId();
    }

    public void borrarUsuario(int idUsuario) {
        controlPersis.borrarUsuario(idUsuario);
    }

    public Usuario traerUsuario(int idUsuario) {
        return controlPersis.traerUsuario(idUsuario);
    }

    public void editarUsuario(Usuario usuario, String usu, String claveUsuario, String rolRecibido) {
        usuario.setNombre(usu);
        usuario.setContrasenia(claveUsuario);

        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);

        if (rolEncontrado != null) {
            usuario.setRol(rolEncontrado);
        }
        
        controlPersis.editarUsuario(usuario);
    }

}
