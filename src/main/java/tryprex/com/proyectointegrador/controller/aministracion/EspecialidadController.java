package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tryprex.com.proyectointegrador.model.bd.Especialidad;
import tryprex.com.proyectointegrador.service.EspecialidadService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
    private EspecialidadService especialidadService;

    @GetMapping("/listar")
    @ResponseBody
    public List<Especialidad> listarEspecialidades(){
        return especialidadService.listarEspecialidad();
    }
}
