package proj.med.API.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import proj.med.API.enums.Especialidade;

public record DadosCadastroMedicoDTO(
    @NotBlank(message = "O nome não pode estar vazio")
    String nome,

    @NotBlank(message = "O email não pode estar vazio")
    @Email
    String email,

    @NotBlank(message = "O telefone não pode estar vazio")
    String telefone,
    @Positive(message = "O crm deve ser um numero positivo")

    @Positive(message = "O crm deve ser um numero positivo")
    @NotNull(message = "O crm é obrigatório")
    Integer crm,

    @NotNull(message = "A especialidade não pode estar vazio")
    Especialidade especialidade,

    @Valid
    @NotNull(message = "Endereco obrigatorio")
    EnderecoDTO endereco
) {}

