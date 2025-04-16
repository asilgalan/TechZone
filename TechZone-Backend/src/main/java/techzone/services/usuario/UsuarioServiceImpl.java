package techzone.services.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.Usuario;
import techzone.models.dto.UsuarioDto;
import techzone.repositories.IUsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
      return usuarioRepository.save(usuario);
    }
    @Transactional
    @Override
    public void eliminarUsuario(Long id) {

        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }

    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
