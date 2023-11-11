package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/frmPaciente")
public class PacienteController {
    @GetMapping("/frmPaciente")
    public String frmPaciente(){
        return "/administracion/frmPaciente";
    }
}
