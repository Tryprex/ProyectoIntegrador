package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idhorario;
    @Column(name = "fechahorario")
    private Date fechahorario;

    @ManyToOne
    @JoinColumn(name = "medicoid")
    private Medico medico;

}
