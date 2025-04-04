package techzone.services.proveedor;

import techzone.models.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
     List<Proveedor> obtenerProveedores();
     Optional<Proveedor> obtenerProveedorPorId(Long id);
     Proveedor guardarProveedor(Proveedor proveedor);
     void eliminarProveedor(Long id);
}
