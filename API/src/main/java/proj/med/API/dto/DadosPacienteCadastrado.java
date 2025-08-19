package proj.med.API.dto;

import proj.med.API.model.Endereco;
import proj.med.API.model.Paciente;

public record DadosPacienteCadastrado(Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {

        public DadosPacienteCadastrado(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getCpf(),
            paciente.getEndereco()
        );
    }

}
