package techzone.services.Favorito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techzone.models.Favorito;
import techzone.models.Producto;
import techzone.models.Usuario;
import techzone.repositories.IFavoritoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IFavoritoServiceImpl implements IFavoritoService {

    @Autowired
  private IFavoritoRepository favoritoRepository;

    @Override
    public List<Favorito> favoritos() {
        return  favoritoRepository.findAll();
    }

    @Override
    public void eliminarFavorito(long id) {
        if(favoritoRepository.existsById(id)) {
            favoritoRepository.deleteById(id);
        }
    }


    @Override
    public Favorito guardarFavorito(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    @Override
    public List<Favorito> favoritosDeUsuario(Long idUsuario) {
        return favoritoRepository.favoritosDeUsuario(idUsuario);
    }

    @Override
    public Optional<Favorito> obtenerFavorito(Long id) {
        return favoritoRepository
                .findById(id);
    }


}
