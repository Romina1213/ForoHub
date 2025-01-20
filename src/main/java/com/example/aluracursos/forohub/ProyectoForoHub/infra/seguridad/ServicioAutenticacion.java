package com.example.aluracursos.forohub.ProyectoForoHub.infra.seguridad;

import com.example.aluracursos.forohub.ProyectoForoHub.dominio.usuario.Usuario;
import com.example.aluracursos.forohub.ProyectoForoHub.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutenticacion implements UserDetailsService {
    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuariosRepositorio.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return usuario;
    }
}
