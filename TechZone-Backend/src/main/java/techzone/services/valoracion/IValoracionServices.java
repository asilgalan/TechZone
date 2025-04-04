package techzone.services.valoracion;

import techzone.models.Categoria;
import techzone.models.Valoracion;

import java.util.List;
import java.util.Optional;

public interface IValoracionServices {
    List<Valoracion> obtenerValoraciones();
    Optional<Valoracion> obtenerValoracion(Long id);
    Valoracion  guardarValoracion(Valoracion valoracion);
    void eliminarValoracion(Long id);

}
