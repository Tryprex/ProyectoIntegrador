package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.Horario;

import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    @Query("SELECT DISTINCT DATE_FORMAT(h.fechahorario, '%Y-%m-%d') FROM Horario h WHERE h.medico.idmedico = :medicoId")
    List<String> obtenerFechasDisponiblesPorMedico(@Param("medicoId") Integer medicoId);

    @Query("SELECT DISTINCT DATE_FORMAT(h.fechahorario, '%H:%i') FROM Horario h WHERE h.medico.idmedico = :medicoId")
    List<String> obtenerHorasDisponiblesPorMedico(@Param("medicoId") Integer medicoId);

    @Query("SELECT h.idhorario FROM Horario h WHERE h.medico.idmedico = :medicoId")
    List<Integer> obtenerIdHorarioPorMedico(@Param("medicoId") Integer medicoId);

}
