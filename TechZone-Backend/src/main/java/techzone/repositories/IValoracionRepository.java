package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzone.models.Valoracion;


@Repository
public interface IValoracionRepository extends JpaRepository<Valoracion, Long> {
}
