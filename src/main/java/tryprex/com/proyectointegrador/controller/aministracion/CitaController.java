package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/frmCita")
public class CitaController {
    @GetMapping("/frmCita")
    public String frmCita(){
        return "/administracion/frmCita";
    }
}
