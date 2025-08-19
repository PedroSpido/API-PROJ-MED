package proj.med.API.Interfaces;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proj.med.API.dto.DadosAgendamentoConsulta;
import proj.med.API.exceptions.ValidacaoException;
import proj.med.API.repository.ConsultaRepository;

@Component
public class PacienteComConsultaEmData implements ConsultaInterface {
    
    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataHora = dados.dataHora();
   
        boolean existeConsultaPaciente = consultaRepository.existsByPacienteIdAndDataHoraConsultaBetween(
                dados.idPaciente(),
                dataHora.toLocalDate().atStartOfDay(),
                dataHora.toLocalDate().atTime(23, 59));

        if (existeConsultaPaciente) {
            throw new ValidacaoException("Paciente ja possui consulta nessa data/hora!");
        }
    }
}
