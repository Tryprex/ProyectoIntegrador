package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.TipoDocumento;
import tryprex.com.proyectointegrador.repository.TipoDocumentoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoDocumentoService {

        private TipoDocumentoRepository tipoDocumentoRepository;

        public List<TipoDocumento> listarTipodocumento(){
            return tipoDocumentoRepository.findAll();
        }

    }
