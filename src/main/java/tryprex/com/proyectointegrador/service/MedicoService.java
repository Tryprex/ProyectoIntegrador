package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.Medico;
import tryprex.com.proyectointegrador.repository.MedicoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class MedicoService {
    private MedicoRepository medicoRepository;

    public List<Medico> listarMedico(){
        return medicoRepository.findAll();
    }

    public List<Medico> listarMedicosPorEspecialidad(int especialidadId) {
        return medicoRepository.findByEspecialidadId(especialidadId);
    }
}
