package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import techzone.models.Carrito;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    @Query("SELECT c FROM Carrito c WHERE c.usuario.idUsuario = :usuarioId")
    Optional<Carrito> findByIdUsuario(@Param("usuarioId") Long usuarioId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carrito c WHERE c.usuario.idUsuario= :usuarioId")
    boolean existsByUsuarioId(@Param("usuarioId") Long usuarioId);
}
