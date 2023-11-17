package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.*;
import tryprex.com.proyectointegrador.model.request.CitaRequest;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.repository.CitaRepository;
import tryprex.com.proyectointegrador.repository.HorarioRepository;
import tryprex.com.proyectointegrador.repository.MedicoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CitaService {
    private CitaRepository citaRepository;
    private HorarioRepository horarioRepository;
    private MedicoRepository medicoRepository;

    public List<Cita> listarCitas(){
        return citaRepository.findAll();
    }
    public ResultadoResponse guardarCita(CitaRequest citaRequest) {
        try {
            // Verificar si la cita ya existe por ID
            if (citaRequest.getIdCita() != null && citaRepository.existsById(citaRequest.getIdCita())) {
                return ResultadoResponse.builder()
                        .respuesta(false)
                        .mensaje("La cita con ID " + citaRequest.getIdCita() + " ya existe.")
                        .build();
            }

            Cita cita = new Cita();

            if (citaRequest.getIdCita() != null) {
                cita.setIdcita(citaRequest.getIdCita());
            }

            Paciente paciente = new Paciente();
            paciente.setIdpaciente(citaRequest.getPacienteId());
            cita.setPaciente(paciente);

            Horario horario = horarioRepository.findById(citaRequest.getHorarioId())
                    .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + citaRequest.getHorarioId()));
            cita.setHorario(horario);

            Sede sede = new Sede();
            sede.setIdsede(citaRequest.getSedeId());
            cita.setSede(sede);

            Medico medico = medicoRepository.findById(citaRequest.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + citaRequest.getMedicoId()));
            horario.setMedico(medico);

            citaRepository.save(cita);

            return ResultadoResponse.builder()
                    .respuesta(true)
                    .mensaje("Cita guardada correctamente")
                    .build();
        } catch (Exception e) {
            return ResultadoResponse.builder()
                    .respuesta(false)
                    .mensaje("Error: " + e.getMessage())
                    .build();
        }
    }

}
