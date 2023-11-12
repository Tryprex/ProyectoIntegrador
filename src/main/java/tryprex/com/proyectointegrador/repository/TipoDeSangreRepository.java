package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.TipoDeSangre;

@Repository
public interface TipoDeSangreRepository extends JpaRepository<TipoDeSangre, Integer> {
}
