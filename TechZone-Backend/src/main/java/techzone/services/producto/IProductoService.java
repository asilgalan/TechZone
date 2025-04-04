package techzone.services.producto;

import techzone.models.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> obtenerProductos();
    Optional<Producto> obtenerProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Long id);
}
