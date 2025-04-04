package techzone.services.valoracion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.Categoria;
import techzone.models.Valoracion;
import techzone.repositories.IValoracionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionServiceImpl implements  IValoracionServices {

    @Autowired
    private IValoracionRepository valoracionRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Valoracion> obtenerValoraciones() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Valoracion> obtenerValoracion(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Valoracion guardarValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    @Transactional
    public void eliminarValoracion(Long id) {
        if(valoracionRepository.existsById(id)) {
            valoracionRepository.deleteById(id);
        }

    }
}
