package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    private Integer idespecialidad;

    @Column(name = "nomespecialidad")
    private String nomespecialidad;

}
