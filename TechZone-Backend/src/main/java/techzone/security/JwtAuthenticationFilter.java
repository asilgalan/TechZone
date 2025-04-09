package techzone.security;


import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import techzone.models.Usuario;
import techzone.repositories.IUsuarioRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static techzone.security.TokenJwtConfig.SECRET_KEY;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private  AuthenticationManager authenticationManager;

   private IUsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, IUsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       String email=null;
       String password=null;
       Usuario usuario=null;
        try {
            usuario=new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
            email=usuario.getEmail();
            password=usuario.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword());
        return this.authenticationManager.authenticate(authRequest);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String email = user.getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("authorities", authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")));

        String token = Jwts.builder()
                .subject(email)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +3600000)) // 1 hora
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();


        response.addHeader("Authorization", "Bearer " + token);

        Map<String, String> body = new HashMap<>();
        body.put("id", usuario.getIdUsuario().toString());
        body.put("email", email);
        body.put("token", token);
        body.put("nombre", usuario.getNombre());
        body.put("usuario", usuario.getUsuario());
        body.put("roles", claims.get("authorities").toString());
        body.put("authStatus", "authenticated");



        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(200);
    }

        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        Map<String, Object> body = new HashMap<>();
        body.put("error", failed.getMessage());
        body.put("message", "Error en la autenticacion del usuario");
        body.put("authStatus", "not-authenticated");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(401);

        }
}
