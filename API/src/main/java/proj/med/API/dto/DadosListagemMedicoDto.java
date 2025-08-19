package proj.med.API.dto;

import proj.med.API.enums.Especialidade;

public record DadosListagemMedicoDto(Long id, String nome, String email, int crm, Especialidade especialidade) {}

