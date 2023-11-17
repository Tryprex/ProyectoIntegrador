package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.Horario;
import tryprex.com.proyectointegrador.repository.HorarioRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    private HorarioRepository horarioRepository;

    public List<Horario> listarHorario() {
        return horarioRepository.findAll();
    }
}
