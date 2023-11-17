package tryprex.com.proyectointegrador.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SedeRequest {

    private Integer idsede;
    private String nomsede;
    private String direccion;
    private String telefono;
}
