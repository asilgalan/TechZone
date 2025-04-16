package techzone.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Categoria;

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


        public ProductoDto(Long idFavorito, ProductoDto dto) {
                this.idProducto = dto.getIdProducto();
                this.nombre = dto.getNombre();
        }
}
