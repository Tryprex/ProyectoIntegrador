package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tryprex.com.proyectointegrador.model.bd.Especialidad;
import tryprex.com.proyectointegrador.repository.EspecialidadRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EspecialidadService {
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarEspecialidad(){
        return especialidadRepository.findAll();
    }


}
