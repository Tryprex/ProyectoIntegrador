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
@Table(name = "tipodocumento")
public class TipoDocumento {

    @Id
    private Integer idtipodocumento;

    @Column(name = "tipodocumento")
    private String tipodocumento;
}
