package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.TipoDeSangre;
import tryprex.com.proyectointegrador.repository.TipoDeSangreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoDeSangreService {
    private TipoDeSangreRepository tipoDeSangreRepository;

    public List<TipoDeSangre> listarTiposdesangre(){
        return tipoDeSangreRepository.findAll();
    }

}
