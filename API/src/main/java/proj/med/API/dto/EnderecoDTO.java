package proj.med.API.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
    @NotBlank(message = "O logradouro não pode estar vazio")
    String logradouro,
    @NotBlank(message = "O bairro não pode estar vazio")
    String bairro,
    @NotBlank(message = "A cidade não pode estar vazia")
    String cidade,
    @NotBlank(message = "O estado não pode estar vazio")
    String uf,
    @NotBlank(message = "O CEP não pode estar vazio")
    String cep,

    String numero,
    String complemento
) {}

