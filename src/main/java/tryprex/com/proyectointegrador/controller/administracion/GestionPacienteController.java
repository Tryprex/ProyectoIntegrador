package tryprex.com.proyectointegrador.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tryprex.com.proyectointegrador.model.bd.Paciente;
import tryprex.com.proyectointegrador.model.dto.PacienteDto;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.service.PacienteService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/gestionpaciente")
public class GestionPacienteController {

    private final PacienteService pacienteService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("listaPacientes",
                pacienteService.listarPacientes());
        return "administracion/paciente/frmListarActualizar";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @PostMapping("/actualizar")
    @ResponseBody
    public ResultadoResponse actualizarPaciente(
            @RequestBody PacienteDto
                    pacienteDto
    ){
        return pacienteService.actualizarPaciente(pacienteDto);
    }

}
