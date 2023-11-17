package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tryprex.com.proyectointegrador.model.bd.Horario;
import tryprex.com.proyectointegrador.service.EspecialidadService;
import tryprex.com.proyectointegrador.service.HorarioService;
import tryprex.com.proyectointegrador.service.MedicoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/fechasYHorasDisponibles")
    public ResponseEntity<Map<String, Object>> obtenerFechasYHorasDisponibles(@RequestParam(name = "medicoId") Integer medicoId) {
        Map<String, Object> fechasYHorasDisponibles = new HashMap<>();

        List<String> fechasDisponibles = horarioService.obtenerFechasDisponiblesPorMedico(medicoId);
        List<String> horasDisponibles = horarioService.obtenerHorasDisponiblesPorMedico(medicoId);

        List<Integer> idHorarios = horarioService.obtenerIdHorarioPorMedico(medicoId);

        if (!idHorarios.isEmpty()) {
            Integer idHorario = idHorarios.get(0);
            fechasYHorasDisponibles.put("idHorario", idHorario);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        fechasYHorasDisponibles.put("fechas", fechasDisponibles);
        fechasYHorasDisponibles.put("horas", horasDisponibles);

        return new ResponseEntity<>(fechasYHorasDisponibles, HttpStatus.OK);
    }



}
