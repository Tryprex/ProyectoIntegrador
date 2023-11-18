package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tryprex.com.proyectointegrador.model.bd.Medico;
import tryprex.com.proyectointegrador.model.bd.Paciente;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
