package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzone.models.Proveedor;
@Repository
public interface IProveedorRepository  extends JpaRepository<Proveedor, Long> {
}
