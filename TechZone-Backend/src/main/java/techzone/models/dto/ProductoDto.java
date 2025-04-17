package techzone.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Categoria;
import techzone.models.Producto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

        private Long idProducto;
        private String nombre;
        private String slug;
        private String descripcionCorta;
        private String descripcionLarga;
        private String imagenPrincipal;
        private List<String> imagenes;
        private CategoriaSimpleDto categoria;
        private String sku;
        private String modelo;
        private Double precio;
        private Double coste;
        private Boolean oferta;

        public ProductoDto(Producto producto) {
                this.idProducto = producto.getIdProducto();
                this.nombre = producto.getNombre();
                this.slug = producto.getSlug();
                this.descripcionCorta = producto.getDescripcionCorta();
                this.descripcionLarga = producto.getDescripcionLarga();
                this.imagenPrincipal = producto.getImagenPrincipal();
                this.imagenes = producto.getImagenes();
                this.categoria = producto.getCategoria() != null ?
                        new CategoriaSimpleDto(producto.getCategoria()) : null;
                this.sku = producto.getSku();
                this.modelo = producto.getModelo();
                this.precio = producto.getPrecio();
                this.coste = producto.getCoste();
                this.oferta = producto.getOferta();
        }

        public ProductoDto(Long idFavorito, ProductoDto dto) {
                this.idProducto = dto.getIdProducto();
                this.nombre = dto.getNombre();
        }
}
