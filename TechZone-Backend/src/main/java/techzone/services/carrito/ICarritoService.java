package techzone.services.carrito;

import techzone.models.Carrito;
import techzone.models.ItemCarrito;

import java.util.List;

public interface ICarritoService {
    Carrito obtenerCarritoPorUsuario(Long usuarioId);

    ItemCarrito agregarProducto(Long usuarioId, Long productoId, Integer cantidad);

    void eliminarProducto(Long usuarioId, Long productoId);

    void actualizarCantidad(Long usuarioId, Long productoId, Integer nuevaCantidad);

    void vaciarCarrito(Long usuarioId);
}