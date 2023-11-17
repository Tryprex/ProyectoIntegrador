package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcita;

    @ManyToOne
    @JoinColumn(name = "pacienteid")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "horarioid")
    private Horario horario;
    @ManyToOne
    @JoinColumn(name = "sedeid")
    private Sede sede;



}
