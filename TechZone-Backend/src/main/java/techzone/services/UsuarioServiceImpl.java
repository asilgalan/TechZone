package techzone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techzone.models.Usuario;
import techzone.repositories.IUsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
      usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {

        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }

    }
}
