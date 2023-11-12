package tryprex.com.proyectointegrador.controller.aministracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tryprex.com.proyectointegrador.model.bd.TipoUsuario;
import tryprex.com.proyectointegrador.service.TipoUsuarioService;

import java.util.List;
@AllArgsConstructor
@Controller
@RequestMapping("/tipousuario")
public class TipoUsuarioController {

        private TipoUsuarioService tipoUsuarioService;

        @GetMapping("/listar")
        @ResponseBody
        public List<TipoUsuario> listarTipousuario(){
            return tipoUsuarioService.listarTipousuario();
        }
}
