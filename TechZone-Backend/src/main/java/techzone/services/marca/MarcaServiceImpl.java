package techzone.services.marca;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.Marca;
import techzone.repositories.IMarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService {

    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Marca> todasLasMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Marca> buscarMarca(Long id) {
        return marcaRepository.findById(id);
    }

    @Override
    @Transactional
    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    @Transactional
    public void eliminarMarca(Long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
        }

    }

}
