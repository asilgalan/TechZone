package techzone.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Categoria;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaSimpleDto {
    private Integer idCategoria;
    private String nombre;
    private String slug;


    public CategoriaSimpleDto(Categoria categoria) {
        this.idCategoria = categoria.getIdCategoria();
        this.nombre = categoria.getNombre();
        this.slug = categoria.getSlug();

    }
}
