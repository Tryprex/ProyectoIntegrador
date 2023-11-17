package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tryprex.com.proyectointegrador.model.bd.Cita;
import tryprex.com.proyectointegrador.model.bd.Horario;
import tryprex.com.proyectointegrador.model.bd.Medico;
import tryprex.com.proyectointegrador.model.bd.Paciente;
import tryprex.com.proyectointegrador.model.request.CitaRequest;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.service.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("cita")
public class CitaController {
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final HorarioService horarioService;
    private final SedeService sedeService;
    private final CitaService citaService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listaCitas", citaService.listarCitas());
        model.addAttribute("listaPacientes", pacienteService.listarPacientes());
        model.addAttribute("listaMedicos", medicoService.listarMedico());
        model.addAttribute("listaHorarios", horarioService.listarHorario());
        model.addAttribute("listaSedes", sedeService.listarSedes());

        return "backoffice/cita/frmMantCita";
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarCita(
            @RequestBody CitaRequest citaRequest
    ){
        return citaService.guardarCita(citaRequest);
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }


}