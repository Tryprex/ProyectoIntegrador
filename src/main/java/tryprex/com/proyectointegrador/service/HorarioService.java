package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.Horario;
import tryprex.com.proyectointegrador.repository.HorarioRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    private HorarioRepository horarioRepository;

    public List<Horario> listarHorario() {
        return horarioRepository.findAll();
    }

    public List<String> obtenerFechasDisponiblesPorMedico(Integer medicoId) {
        List<String> fechasDisponibles = horarioRepository.obtenerFechasDisponiblesPorMedico(medicoId);
        return fechasDisponibles;
    }

    public List<String> obtenerHorasDisponiblesPorMedico(Integer medicoId) {
        List<String> horasDisponibles = horarioRepository.obtenerHorasDisponiblesPorMedico(medicoId);
        return horasDisponibles;
    }

    public List<Integer> obtenerIdHorarioPorMedico(Integer medicoId) {
        return horarioRepository.obtenerIdHorarioPorMedico(medicoId);
    }

}
