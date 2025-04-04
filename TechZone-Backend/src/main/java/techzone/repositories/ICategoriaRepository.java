package techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import techzone.models.Categoria;
@Repository
public interface ICategoriaRepository  extends JpaRepository<Categoria, Long> {

}
