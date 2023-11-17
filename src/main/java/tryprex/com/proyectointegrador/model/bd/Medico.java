package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmedico;
    @Column(name = "nommedico")
    private String nommedico;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "dni")
    private String cmp;
    @ManyToOne
    @JoinColumn(name = "especialidadid")
    private Medico especialidad;




}
