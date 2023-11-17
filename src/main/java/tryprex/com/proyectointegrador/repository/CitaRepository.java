package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Integer> {

}
