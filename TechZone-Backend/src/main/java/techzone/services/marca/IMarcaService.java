package techzone.services.marca;


import techzone.models.Marca;

import java.util.List;
import java.util.Optional;


public interface IMarcaService {
    List<Marca> todasLasMarcas();
      Optional<Marca> buscarMarca(Long id);
    Marca guardarMarca(Marca marca);
    void eliminarMarca(Long id);
}
