package tryprex.com.proyectointegrador.controller.administracion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tryprex.com.proyectointegrador.model.bd.Paciente;
import tryprex.com.proyectointegrador.model.dto.LoginDTO;
import tryprex.com.proyectointegrador.repository.PacienteRepository;

import java.util.List;

@Controller

@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") LoginDTO usuario, Model model) {
        List<Paciente> pacientesEncontrados = pacienteRepository.login(usuario.getDni(), usuario.getContrasena());
        log.info("Pacientes", pacientesEncontrados);
        if (pacientesEncontrados.isEmpty()) {
            model.addAttribute("error", "Usuario y/o contrasena invalidos");
            return "login";

        }else{
            return "home";
        }

    }

}
