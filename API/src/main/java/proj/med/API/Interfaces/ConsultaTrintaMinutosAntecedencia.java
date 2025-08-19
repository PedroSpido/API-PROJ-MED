package proj.med.API.Interfaces;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import proj.med.API.dto.DadosAgendamentoConsulta;
import proj.med.API.exceptions.ValidacaoException;

@Component
public class ConsultaTrintaMinutosAntecedencia implements ConsultaInterface {
    
    @Override
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataHora = dados.dataHora();
 
        if (Duration.between(LocalDateTime.now(), dataHora).toMinutes() < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
        }
    }
}



