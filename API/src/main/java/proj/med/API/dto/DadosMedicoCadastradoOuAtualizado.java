package proj.med.API.dto;

import proj.med.API.enums.Especialidade;
import proj.med.API.model.Endereco;
import proj.med.API.model.Medico;

public record DadosMedicoCadastradoOuAtualizado(Long id, String nome, String email, String telefone, int crm, Especialidade especialidade, Endereco endereco) {

    public DadosMedicoCadastradoOuAtualizado(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
