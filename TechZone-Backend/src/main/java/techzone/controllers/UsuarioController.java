package techzone.controllers;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import techzone.models.Enums.Roles;
import techzone.models.Usuario;
import techzone.services.usuario.IUsuarioService;
import techzone.services.usuario.UsuarioDetailService;

import java.io.IOException;
import java.security.Key;
import java.util.*;

import static io.jsonwebtoken.Jwts.*;
import static techzone.security.TokenJwtConfig.SECRET_KEY;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    private UsuarioDetailService usuarioDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if(usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.setRoles(Collections.singleton(Roles.USER));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> ActualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        Optional<Usuario> UsuarioPorid = usuarioService.obtenerPorId(id);
        if (UsuarioPorid.isPresent()) {
            usuario.setIdUsuario(id);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
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

    @GetMapping("/check-status")
    public ResponseEntity<?> checkTokenStatus(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "not-authenticated", "message", "Token no proporcionado"));
        }
        String token = authHeader.replace("Bearer ", "");
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return ResponseEntity.ok(Map.of("status", "authenticated"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "not-authenticated", "message", e.getMessage()));
        }
    }
}
