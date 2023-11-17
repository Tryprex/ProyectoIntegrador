package tryprex.com.proyectointegrador.model.request;

import lombok.Data;
@Data
public class CitaRequest {
    private Integer idCita;
    private Integer pacienteId;
    private Integer sedeId;
    private Integer horarioId;
    private Integer medicoId;
}