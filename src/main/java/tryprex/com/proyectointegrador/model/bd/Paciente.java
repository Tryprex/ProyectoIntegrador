package tryprex.com.proyectointegrador.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpaciente;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidospa")
    private String apellidospa;
    @Column(name = "apellidosma")
    private String apellidosma;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechanacimiento")
    private String fechanacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "peso")
    private String peso;
    @Column(name = "altura")
    private String altura;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "tipodocumentoid")
    private TipoDocumento tipodocumento;

    @ManyToOne
    @JoinColumn(name = "tipodesangreid")
    private TipoDeSangre tipodesangre;

    @ManyToOne
    @JoinColumn(name = "sedeid")
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "tipousuarioid")
    private TipoUsuario tipousuario;
}
