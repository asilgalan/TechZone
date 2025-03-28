package techzone.services;

import techzone.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> obtenerUsuarios();
    Optional<Usuario> obtenerPorId(Long id);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
}
