package proj.med.API.dto;

import jakarta.validation.constraints.NotNull;
import proj.med.API.enums.MotivoCancelamentoConsulta;

public record DadosCancelamentoConsultaDto(
    @NotNull
    Long idConsulta,
    
    @NotNull
    MotivoCancelamentoConsulta motivoCancelamentoConsulta
) {
}
