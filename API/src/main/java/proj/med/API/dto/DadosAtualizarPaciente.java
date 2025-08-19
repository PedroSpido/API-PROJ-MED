package proj.med.API.dto;

public record DadosAtualizarPaciente(
    Long id,
    String nome,
    String telefone,
    EnderecoDTO endereco
) {}
