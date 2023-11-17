package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tryprex.com.proyectointegrador.model.bd.Especialidad;
import tryprex.com.proyectointegrador.model.bd.Medico;
import tryprex.com.proyectointegrador.service.EspecialidadService;
import tryprex.com.proyectointegrador.service.MedicoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/medico")
public class MedicoController {
    private final MedicoService medicoService;
    private final EspecialidadService especialidadService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listaMedicos",
                medicoService.listarMedico());
        model.addAttribute("listaEspecialidades", especialidadService.listarEspecialidad());
        return "backoffice/medico/frmMantMedico";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Medico> listarMedicos(){
        return medicoService.listarMedico();
    }

    @GetMapping("/listars")
    @ResponseBody
    public List<Especialidad> listaEspecialidad(){
        return especialidadService.listarEspecialidad();
    }

    @GetMapping("/buscarPorEspecialidad")
    @ResponseBody
    public List<Medico> buscarPorEspecialidad(@RequestParam(name = "especialidadId", required = false) Integer especialidadId) {
        List<Medico> medicos;

        if (especialidadId != null) {
            medicos = medicoService.listarMedicosPorEspecialidad(especialidadId);
        } else {
            medicos = medicoService.listarMedico();
        }

        return medicos;
    }






}
