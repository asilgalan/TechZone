package techzone.services.proveedor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techzone.models.Proveedor;
import techzone.repositories.IProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService {
      @Autowired
      private IProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> obtenerProveedores() {
        return proveedorRepository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Proveedor> obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }


    @Override
    @Transactional
    public Proveedor guardarProveedor(Proveedor proveedor) {
           return proveedorRepository.save(proveedor);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long id) {
        if(proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
        }
    }
}
