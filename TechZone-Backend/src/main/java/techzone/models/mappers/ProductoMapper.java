package techzone.models.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techzone.models.Producto;
import techzone.models.dto.ProductoDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    @Autowired
    private CategoriaMapper categoriaMapper;

    public ProductoDto toDto(Producto producto) {
        ProductoDto dto = new ProductoDto();

        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setSlug(producto.getSlug());
        dto.setDescripcionCorta(producto.getDescripcionCorta());
        dto.setDescripcionLarga(producto.getDescripcionLarga());
        dto.setImagenPrincipal(producto.getImagenPrincipal());
        dto.setImagenes(producto.getImagenes());
        dto.setPrecio(producto.getPrecio());
        dto.setCoste(producto.getCoste());
        dto.setSku(producto.getSku());
        dto.setModelo(producto.getModelo());
        dto.setPrecio(producto.getPrecio());
        dto.setOferta(producto.getOferta());
        if(producto.getCategoria() != null) {
            dto.setCategoria(categoriaMapper.toSimpleDto(producto.getCategoria()));
        }

        return dto;
    }

    public List<ProductoDto> toDtoList(List<Producto> productos) {
        return productos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}