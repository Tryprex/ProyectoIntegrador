package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tryprex.com.proyectointegrador.model.bd.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}
