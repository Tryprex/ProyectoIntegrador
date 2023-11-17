package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.*;
import tryprex.com.proyectointegrador.model.dto.PacienteDto;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public ResultadoResponse registrarPaciente(PacienteDto paciente) {
        String mensaje = "Paciente registrado correctamente";
        Boolean respuesta = true;
        try {
            Paciente objPaciente = new Paciente();

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




    public ResultadoResponse actualizarPaciente(PacienteDto paciente) {
        String mensaje = "Paciente actualizado correctamente";
        Boolean respuesta = true;

        try {
            Paciente objPaciente = new Paciente();

            // Verificar si el paciente ya existe (por el ID)
            if (paciente.getIdpaciente() != null) {
                Optional<Paciente> pacienteExistente = pacienteRepository.findById(paciente.getIdpaciente());

                if (pacienteExistente.isPresent()) {
                    objPaciente = pacienteExistente.get();
                } else {
                    mensaje = "Paciente no encontrado para actualizar";
                    respuesta = false;
                    return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
                }
            }

            // Actualizar los datos del paciente con la información de la solicitud
            TipoUsuario tipousuario = new TipoUsuario();
            tipousuario.setIdtipousuario(paciente.getTipousuario());
            objPaciente.setTipousuario(tipousuario);

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

            TipoDocumento tipodocumento = new TipoDocumento();
            tipodocumento.setIdtipodocumento(paciente.getTipodocumento());
            objPaciente.setTipodocumento(tipodocumento);

            TipoDeSangre tipodesangre = new TipoDeSangre();
            tipodesangre.setIdtipodesangre(paciente.getTipodesangre());
            objPaciente.setTipodesangre(tipodesangre);

            Sede sede = new Sede();
            sede.setIdsede(paciente.getSede());
            objPaciente.setSede(sede);

            // Guardar el paciente actualizado en la base de datos
            pacienteRepository.save(objPaciente);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            mensaje = "Error al actualizar el paciente";
            respuesta = false;
        }

        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}
