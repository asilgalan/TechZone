package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import techzone.models.Favorito;

import java.util.List;

@Repository
public interface IFavoritoRepository extends JpaRepository<Favorito, Long> {

    @NativeQuery("SELECT * FROM Favoritos WHERE id_usuario = ?1")
    List<Favorito> favoritosDeUsuario(Long idUsuario);


}
