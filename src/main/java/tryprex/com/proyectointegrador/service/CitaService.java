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

    public List<Cita> listarCitas(){
        return citaRepository.findAll();
    }

    public ResultadoResponse  guardarActualizarCita(CitaDTO citaDTO){

        String mensaje = "Sala registrada Correctamente";
        boolean respuesta = true;
        try{
            Cita nuevacita= new Cita();
            if (citaDTO.getIdcita() > 0){
                nuevacita.setIdcita(citaDTO.getIdcita());
            }
            nuevacita.setDescsala((citaDTO.getDescsala()));
            nuevacita.setAsientos(citaDTO.getAsientos());
            Estado estado = new Estado();
            estado.setIdestado(citaDTO.getIdeestado());
            nuevacita.setEstado(estado);
            salaRepository.save(nuevacita);
        }catch (Exception ex){
            mensaje = "Cita NO registrada";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
