package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;

@Entity
@Table(name ="citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcita;


    @ManyToOne
    @JoinColumn(name = "idpaciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "idhorario")
    private Horario horario;
    @OneToOne
    @JoinColumn(name = "idsede")
    private Sede sede;
}
