package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tryprex.com.proyectointegrador.model.bd.Sede;
import tryprex.com.proyectointegrador.service.SedeService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/sede")
public class SedeController {

    private SedeService sedeService;

    @GetMapping("/frmSede")
    public String frmSede(){
        return "/administracion/frmSede";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Sede> listarSedes(){
        return sedeService.listarSedes();
    }
}
