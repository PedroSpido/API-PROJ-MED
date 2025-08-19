package proj.med.API.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
    @NotBlank(message = "O email não pode estar vazio")
    String nome,
    @NotBlank(message = "O email não pode estar vazio")
    String email,
    @NotBlank(message = "O telefone não pode estar vazio")
    String telefone,
    @NotBlank(message = "O cpf não pode estar vazio")
    String cpf,
    @Valid
    @NotNull(message = "O endereco nao pode estar vazio")
    EnderecoDTO endereco
) {}
