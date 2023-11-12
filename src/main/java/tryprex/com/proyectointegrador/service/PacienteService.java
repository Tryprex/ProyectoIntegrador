package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.*;
import tryprex.com.proyectointegrador.model.request.PacienteRequest;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.repository.PacienteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public ResultadoResponse guardarPaciente(PacienteRequest paciente) {
        String mensaje = "Paciente registrado correctamente";
        Boolean respuesta = true;
        try {
            Paciente objPaciente = new Paciente();
            if (paciente.getIdpaciente() != null) {
                objPaciente.setIdpaciente(paciente.getIdpaciente());
            }
            TipoDocumento tipodocumento = new TipoDocumento();
            tipodocumento.setIdtipodocumento(paciente.getTipodocumento());
            objPaciente.setTipodocumento(tipodocumento);

            objPaciente.setDni(paciente.getDni());
            objPaciente.setNombres(paciente.getNombres());
            objPaciente.setApellidospa(paciente.getApellidospa());
            objPaciente.setApellidosma(paciente.getApellidosma());
            objPaciente.setTelefono(paciente.getTelefono());
            objPaciente.setFechanacimiento(paciente.getFechanacimiento());
            objPaciente.setSexo(paciente.getSexo());
            objPaciente.setPeso(paciente.getPeso());
            objPaciente.setAltura(paciente.getAltura());
            objPaciente.setCorreo(paciente.getCorreo());
            objPaciente.setContrasena(paciente.getContrasena());

            TipoUsuario tipousuario = new TipoUsuario();
            tipousuario.setIdtipousuario(paciente.getTipousuario());
            objPaciente.setTipousuario(tipousuario);

            TipoDeSangre tipodesangre = new TipoDeSangre();
            tipodesangre.setIdtipodesangre(paciente.getTipodesangre());
            objPaciente.setTipodesangre(tipodesangre);

            Sede sede = new Sede();
            sede.setIdsede(paciente.getSede());
            objPaciente.setSede(sede);

            pacienteRepository.save(objPaciente);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();  // Imprime la pila de llamadas de la excepción
            mensaje = "Paciente no registrado";
            respuesta = false;

        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
