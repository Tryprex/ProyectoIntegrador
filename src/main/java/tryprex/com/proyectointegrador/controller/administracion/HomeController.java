package tryprex.com.proyectointegrador.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/home")
    public String index(){
        return "home";
    }

}
