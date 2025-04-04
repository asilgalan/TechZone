package techzone.services.categoria;

import techzone.models.Categoria;

import java.util.List;
import java.util.Optional;


public interface ICategoriaService {

    List<Categoria> obternerCategorias();
    Optional<Categoria> obtenerCategoria(Long id);
    Categoria guardarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);
}
