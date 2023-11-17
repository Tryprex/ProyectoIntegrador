package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.Medico;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    @Query("SELECT m FROM Medico m WHERE m.especialidad.id = :especialidadId")
    List<Medico> findByEspecialidadId(@Param("especialidadId") int especialidadId);

}
