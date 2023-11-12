package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.TipoUsuario;
import tryprex.com.proyectointegrador.repository.TipoUsuarioRespository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoUsuarioService {
    private TipoUsuarioRespository tipoUsuarioRespository;

    public List<TipoUsuario> listarTipousuario(){
        return tipoUsuarioRespository.findAll();
    }
}
