package techzone.services.carrito;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.*;

import techzone.repositories.*;


import java.util.Optional;

@Service
@AllArgsConstructor
public class CarritoService implements ICarritoService {
    private final ICarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final IProductoRepository productoRepository;
    private final IUsuarioRepository usuarioRepository;


    @Transactional
    public Carrito obtenerCarritoPorUsuario(Long usuarioId) {
        return carritoRepository.
                findByIdUsuario(usuarioId)
                .orElseGet(() -> crearNuevoCarrito(usuarioId));
    }

    private Carrito crearNuevoCarrito(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        return carritoRepository.save(carrito);
    }

    @Transactional
    public ItemCarrito agregarProducto(Long usuarioId, Long productoId, Integer cantidad) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar si el producto ya esta en el carrito
        Optional<ItemCarrito> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getProducto().getIdProducto().equals(productoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemCarrito item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            return itemCarritoRepository.save(item);
        } else {
            ItemCarrito nuevoItem = new ItemCarrito();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);
            carrito.getItems().add(nuevoItem);
            return itemCarritoRepository.save(nuevoItem);
        }
    }

    @Transactional
    public void eliminarProducto(Long usuarioId, Long productoId) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        ItemCarrito item = carrito.getItems().stream()
                .filter(i -> i.getProducto().getIdProducto().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));
        carrito.getItems().remove(item);
        itemCarritoRepository.delete(item);
    }

    @Transactional
    public void actualizarCantidad(Long usuarioId, Long productoId, Integer nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            eliminarProducto(usuarioId, productoId);
            return;
        }

        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        ItemCarrito item = carrito.getItems().stream()
                .filter(i -> i.getProducto().getIdProducto().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        item.setCantidad(nuevaCantidad);
        itemCarritoRepository.save(item);
    }

    @Transactional
    public void vaciarCarrito(Long usuarioId) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        itemCarritoRepository.deleteAllByCarritoId(carrito.getIdCarrito());
        carrito.getItems().clear();
    }
}
