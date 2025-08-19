package proj.med.API.dto;

import jakarta.validation.constraints.NotNull;
import proj.med.API.enums.Especialidade;

public record DadosAtualizarMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    EnderecoDTO endereco,
    String email,
    Integer crm,
    Especialidade especialidade
) {}
