package techzone.models.mappers;

import org.springframework.stereotype.Component;
import techzone.models.Categoria;
import techzone.models.dto.CategoriaSimpleDto;

@Component
public class CategoriaMapper {


        public CategoriaSimpleDto toSimpleDto(Categoria categoria) {
            if (categoria == null) {
                return null;
            }
            return new CategoriaSimpleDto(
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getSlug()
            );
        }

}
