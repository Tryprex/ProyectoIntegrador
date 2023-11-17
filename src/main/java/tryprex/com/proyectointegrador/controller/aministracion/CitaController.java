package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tryprex.com.proyectointegrador.model.bd.Paciente;
import tryprex.com.proyectointegrador.service.HorarioService;
import tryprex.com.proyectointegrador.service.MedicoService;
import tryprex.com.proyectointegrador.service.PacienteService;
import tryprex.com.proyectointegrador.service.SedeService;

@AllArgsConstructor
@Controller
@RequestMapping("cita")
public class CitaController {
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final HorarioService horarioService;
    private final SedeService sedeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listaCitas",
                medicoService.listarMedico());
        model.addAttribute("listaPacientes", pacienteService.listarPacientes());
        model.addAttribute("listaMedicos", medicoService.listarMedico());
        model.addAttribute("listaHorarios", horarioService.listarHorario());
        model.addAttribute("listaSedes", sedeService.listarSedes());

        return "backoffice/cita/frmMantCita";
    }
}