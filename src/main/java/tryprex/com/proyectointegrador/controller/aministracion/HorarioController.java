package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tryprex.com.proyectointegrador.service.EspecialidadService;
import tryprex.com.proyectointegrador.service.HorarioService;
import tryprex.com.proyectointegrador.service.MedicoService;

@AllArgsConstructor
@Controller
@RequestMapping("/horario")
public class HorarioController {
    private final HorarioService horarioService;
    private final MedicoService medicoService;
    private final EspecialidadService especialidadService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listaHorarios", horarioService.listarHorario());
        model.addAttribute("listaMedicos",
                medicoService.listarMedico());
        model.addAttribute("listaEspecialidades", especialidadService.listarEspecialidad());
        return "backoffice/horario/frmMantHorario";
    }
}
