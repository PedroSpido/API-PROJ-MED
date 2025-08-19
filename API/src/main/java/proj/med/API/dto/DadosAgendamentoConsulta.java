package proj.med.API.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
    @NotBlank(message= "Campo paciente é obrigatório")
    Long idPaciente,
    Long idMedico,

    @NotNull
    @Future
    @NotBlank(message= "Campo data/hora é obrigatório")
    LocalDateTime dataHora 
) {}
