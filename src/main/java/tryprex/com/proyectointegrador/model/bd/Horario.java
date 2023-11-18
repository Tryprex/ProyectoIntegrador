package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idhorario;

    @Column(name = "fechahorario")
    private Date fechaHorario;


    @ManyToOne
    @JoinColumn(name = "medicoid")
    private Medico medicoid;
}
