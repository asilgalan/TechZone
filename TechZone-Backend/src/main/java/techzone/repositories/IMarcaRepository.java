package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzone.models.Marca;


@Repository
public interface IMarcaRepository  extends JpaRepository<Marca, Long> {

}
