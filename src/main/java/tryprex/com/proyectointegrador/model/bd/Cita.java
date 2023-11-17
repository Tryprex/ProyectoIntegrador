package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Cita {

    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = "cita")
    public class cita {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idcita;

        @ManyToOne
        @JoinColumn(name = "pacienteid")
        private Paciente pacienteid;

        @ManyToOne
        @JoinColumn(name = "sedeid")
        private Sede sedeid;

        @ManyToOne
        @JoinColumn(name = "horarioid")
        private Horario horario;

        @ManyToOne
        @JoinColumn(name = "medicoid")
        private Medico medicoid;
    }
}
