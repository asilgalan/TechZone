package techzone.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoFavoritoDto {
    private ProductoDto  producto;
    private Long idFavorito;
}
