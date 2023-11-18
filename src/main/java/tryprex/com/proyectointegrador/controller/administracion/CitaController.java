package tryprex.com.proyectointegrador.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tryprex.com.proyectointegrador.model.dto.CitaDTO;
import tryprex.com.proyectointegrador.service.CitaService;

@AllArgsConstructor
@Controller
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    CitaService citaService;

    @GetMapping("")
    public String index(Model model) {
        {
            model.addAttribute("listaPacientes",
                    citaService.listarPacientes());

            model.addAttribute("listaMedicos",
                    citaService.listarMedicos());
            model.addAttribute("listaHorarios",
                    citaService.listarHorarios());

            model.addAttribute("listaSedes",
                    citaService.listarSede());

            return "administracion/cita/frmCita";
        }
    }

    @PostMapping("")
    public String agregarCita(@ModelAttribute("cita") CitaDTO cita, Model model) {
        citaService.registrarCita(cita);
        return index(model);

    }


}
