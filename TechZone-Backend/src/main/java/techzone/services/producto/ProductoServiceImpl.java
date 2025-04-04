package techzone.services.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.Producto;
import techzone.repositories.IProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl  implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Override
    @Transactional(readOnly=true)
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional()
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional()
    public void eliminarProducto(Long id) {

        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }

    }
}
