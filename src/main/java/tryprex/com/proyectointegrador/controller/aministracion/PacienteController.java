package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tryprex.com.proyectointegrador.model.bd.Paciente;
import tryprex.com.proyectointegrador.model.bd.TipoDeSangre;
import tryprex.com.proyectointegrador.model.request.PacienteRequest;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.service.PacienteService;
import tryprex.com.proyectointegrador.service.TipoDeSangreService;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    private  final TipoDeSangreService tipoDeSangreService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listaPacientes",
                            pacienteService.listarPacientes());
        return "backoffice/paciente/frmMantPaciente";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/listars")
    @ResponseBody
    public List<TipoDeSangre> listaTiposdesangre() {
        return tipoDeSangreService.listarTiposdesangre();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarPaciente(
            @RequestBody PacienteRequest pacienteRequest
    ){
        return pacienteService.guardarPaciente(pacienteRequest);
    }
}
