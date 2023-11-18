package tryprex.com.proyectointegrador.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tryprex.com.proyectointegrador.model.bd.*;
import tryprex.com.proyectointegrador.model.dto.CitaDTO;
import tryprex.com.proyectointegrador.model.response.ResultadoResponse;
import tryprex.com.proyectointegrador.repository.*;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public ResultadoResponse registrarCita(CitaDTO cita) {
        String mensaje = "Paciente registrado correctamente";
        Boolean respuesta = true;
        try {
            Cita objCita = new Cita();

            Paciente paciente = pacienteRepository.findById(cita.getPaciente()).orElseThrow();

            Horario horario = horarioRepository.findById(cita.getHorario()).orElseThrow();

            Medico medico = medicoRepository.findById(cita.getMedico()).orElseThrow();
            Sede sede = sedeRepository.findById(cita.getSede()).orElseThrow();

            objCita.setPacienteid(paciente);
            objCita.setHorario(horario);
            objCita.setMedicoid(medico);
            objCita.setSedeid(sede);


            citaRepository.save(objCita);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();  // Imprime la pila de llamadas de la excepción
            mensaje = "Cita no registrada";
            respuesta = false;

        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public List<Sede> listarSede() {
        return sedeRepository.findAll();
    }

    public List<Horario> listarHorarios() {

        return horarioRepository.findAll();
    }


}
