package com.stockcontroll.cotroller;

import com.stockcontroll.data.UsuarioDao;
import com.stockcontroll.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioDao usuarioDao;


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = usuarioDao.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioDao.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }


}