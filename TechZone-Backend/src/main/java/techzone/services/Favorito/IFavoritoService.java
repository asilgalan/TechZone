package techzone.services.Favorito;

import techzone.models.Favorito;


import java.util.List;
import java.util.Optional;

public interface IFavoritoService {

    List<Favorito> favoritos();
    void eliminarFavorito(long id);
    Favorito guardarFavorito(Favorito favorito);
    List<Favorito> favoritosDeUsuario(Long idUsuario);
    Optional<Favorito> obtenerFavorito(Long id);
}
