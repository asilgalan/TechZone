package techzone.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaSimpleDto {
    private Integer idCategoria;
    private String nombre;
    private String slug;
}
