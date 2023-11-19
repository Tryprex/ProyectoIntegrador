package tryprex.com.proyectointegrador.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tryprex.com.proyectointegrador.model.dto.PacienteDto;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.service.PacienteService;


@Controller
@AllArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("")
    public String index(Model model) {
        return "administracion/paciente/frmRegistrarPaciente";
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse registrarPaciente(
            @RequestBody PacienteDto pacienteRequest
    ){
        return pacienteService.registrarPaciente(pacienteRequest);
    }



}
