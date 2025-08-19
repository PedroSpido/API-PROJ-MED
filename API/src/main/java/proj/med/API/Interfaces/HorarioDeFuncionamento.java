package proj.med.API.Interfaces;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import proj.med.API.dto.DadosAgendamentoConsulta;
import proj.med.API.exceptions.ValidacaoException;

@Component
public class HorarioDeFuncionamento implements ConsultaInterface {
    
    @Override
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataHora = dados.dataHora();
        DayOfWeek dia = dataHora.getDayOfWeek();
        int hora = dataHora.getHour();

        // horario de funcionamento = segunda a sabado, 7 as 18
        if (dia == DayOfWeek.SUNDAY || hora < 7 || hora > 18) {
            throw new ValidacaoException(
                    "Voce esta tentando agendar uma consulta fora do horario de funcionamento!");
        }
 
    }
}
