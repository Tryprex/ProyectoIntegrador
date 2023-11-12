package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.Sede;
import tryprex.com.proyectointegrador.repository.SedeRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class SedeService {
    private SedeRepository sedeRepository;

    public List<Sede> listarSedes(){
        return sedeRepository.findAll();
    }

}
