package tryprex.com.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tryprex.com.proyectointegrador.model.bd.Paciente;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query(value = "SELECT * FROM paciente WHERE dni = :dni and contrasena = :contrasena", nativeQuery = true) // Reemplaza 'nombre' con el nombre del atributo
    List<Paciente> login(String dni, String contrasena);
}
