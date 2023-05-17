package com.cursojava.ejemplo.controllers;

import com.cursojava.ejemplo.dao.UsuarioDao;
import com.cursojava.ejemplo.models.Usuario;
import com.cursojava.ejemplo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("123456789");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        String usuarioId = jwtUtil.getKey(token);
        if(usuarioId == null){
            return new ArrayList<>();
        }

        return usuarioDao.getUsuarios();
        /*List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("123456789");

        Usuario usuario2 = new Usuario();
        usuario2.setId(5L);
        usuario2.setNombre("Marcos");
        usuario2.setApellido("Gil");
        usuario2.setEmail("marcos@gmail.com");
        usuario2.setTelefono("12345549");

        usuarios.add(usuario);
        usuarios.add(usuario2);

        return usuarios;*/
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable Long id){
       usuarioDao.deleteUsuario(id);
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void postUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.postUsuario(usuario);
    }
}
