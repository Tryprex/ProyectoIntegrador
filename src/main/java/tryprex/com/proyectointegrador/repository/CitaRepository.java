package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.Cita;
import tryprex.com.proyectointegrador.model.bd.Horario;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.horario.medico.idmedico = :idMedico AND c.horario.fechahorario = :fechaHorario")
    int countCitasEnHorario(@Param("idMedico") Integer idMedico, @Param("fechaHorario") Date fechaHorario);
}
