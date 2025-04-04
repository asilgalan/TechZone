package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzone.models.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
