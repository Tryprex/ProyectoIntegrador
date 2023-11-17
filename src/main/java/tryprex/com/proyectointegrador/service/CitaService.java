package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.*;
import tryprex.com.proyectointegrador.model.dto.CitaDTO;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.repository.CitaRepository;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CitaService {

    private CitaRepository citaRepository;

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public ResultadoResponse registrarCita(CitaDTO cita) {
        String mensaje = "Paciente registrado correctamente";
        Boolean respuesta = true;
        try {
            Cita objCita = new Cita();

            Paciente paciente = new Paciente();
            paciente.setIdpaciente(cita.getPacienteid());
            objCita.set

            Sede sede = new Sede();
            sede.setIdsede(cita.getSedeid());
            objCita.setIdsede(sede);

            Horario horario = new Horario();
            horario.setIdHorario(cita.getHorario());
            objCita.set(tipodesangre);

            Medico medico = new Medico();
            medico.setIdmedico(medico.getIdmedico());
            objCita.set(paciente);

            citaRepository.save(objCita);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();  // Imprime la pila de llamadas de la excepción
            mensaje = "Cita no registrada";
            respuesta = false;

        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}
