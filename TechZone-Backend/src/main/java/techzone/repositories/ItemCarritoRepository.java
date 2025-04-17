package techzone.repositories;

import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.ItemCarrito;

import java.util.Optional;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ItemCarrito i WHERE i.carrito.idCarrito = :carritoId")
    void deleteAllByCarritoId(@Param("carritoId") Long carritoId);

    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito.idCarrito = :carritoId AND i.producto.idProducto = :productoId")
    Optional<ItemCarrito> findByCarritoAndProducto(
            @Param("carritoId") Long carritoId,
            @Param("productoId") Long productoId);

    @Query("SELECT SUM(i.cantidad) FROM ItemCarrito i WHERE i.carrito.idCarrito = :carritoId")
    Integer countItemsByCarritoId(@Param("carritoId") Long carritoId);

    @Query("SELECT SUM(i.producto.precio * i.cantidad) FROM ItemCarrito i WHERE i.carrito.idCarrito = :carritoId")
    Double calculateTotalByCarritoId(@Param("carritoId") Long carritoId);
}
