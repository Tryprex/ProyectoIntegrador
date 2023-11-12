package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tryprex.com.proyectointegrador.model.bd.TipoDeSangre;
import tryprex.com.proyectointegrador.service.TipoDeSangreService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/tipodesangre")
public class TipoDeSangreController {

    private TipoDeSangreService tipoDeSangreService;

    @GetMapping("/listar")
    @ResponseBody
    public List<TipoDeSangre> listarTipodesangre(){
        return tipoDeSangreService.listarTiposdesangre();
    }
}
