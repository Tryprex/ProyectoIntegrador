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
@Table(name = "tipousuario")
public class TipoUsuario {

        @Id
        private Integer idtipousuario;

        @Column(name = "nomusuario")
        private String nomusuario;
}


