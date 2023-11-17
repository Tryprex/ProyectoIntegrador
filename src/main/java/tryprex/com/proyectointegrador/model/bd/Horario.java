package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;
    @Column(name = "dni")
    private String fechaHorario;
    @Column(name = "nombres")
    private String nombres;

    @ManyToOne
    @JoinColumn(name = "medicoid")
    private Medico medicoid;
}
