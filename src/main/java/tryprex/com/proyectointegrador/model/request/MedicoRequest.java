package tryprex.com.proyectointegrador.model.request;

import lombok.Data;


@Data
public class MedicoRequest {
    private Integer idmedico;
    private String nommedico;
    private String apellido;
    private String dni;
    private String cmp;
    private Integer especialidad;

}
