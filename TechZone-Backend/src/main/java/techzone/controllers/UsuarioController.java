package techzone.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzone.models.Usuario;
import techzone.services.usuario.IUsuarioService;

import java.util.List;
import java.util.Optional;


/* cada metodo por asi decirlo es una api */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable long id) {
        Optional<Usuario> UsuarioPorid=usuarioService.obtenerPorId(id);
        if (UsuarioPorid.isPresent()) {
            return ResponseEntity.ok(UsuarioPorid);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> AddUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> ActualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        Optional<Usuario> UsuarioPorid = usuarioService.obtenerPorId(id);
        if (UsuarioPorid.isPresent()) {
            usuario.setIdUsuario(id);
            return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable long id) {
        Optional<Usuario> UsuarioPorid = usuarioService.obtenerPorId(id);
        if (UsuarioPorid.isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
