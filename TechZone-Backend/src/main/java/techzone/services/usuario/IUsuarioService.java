package techzone.services.usuario;

import techzone.models.Usuario;
import techzone.models.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService {

    List<Usuario> obtenerUsuarios();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    Optional<Usuario> findByEmail(String email);
}
