package proj.med.API.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import proj.med.API.dto.DadosAtualizarPaciente;
import proj.med.API.dto.DadosCadastroPaciente;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo = true;

    @Embedded
    private Endereco endereco;

    public Paciente(){
    }

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente){
        this.nome = dadosCadastroPaciente.nome();
        this.email = dadosCadastroPaciente.email();
        this.telefone = dadosCadastroPaciente.telefone();
        this.cpf = dadosCadastroPaciente.cpf();
        this.endereco = new Endereco(dadosCadastroPaciente.endereco());
    }

    public void atualizarInfosPaciente(DadosAtualizarPaciente dadosAtualizarPaciente){
        if(dadosAtualizarPaciente.nome() != null) this.nome = dadosAtualizarPaciente.nome();
        if(dadosAtualizarPaciente.telefone() != null) this.telefone = dadosAtualizarPaciente.telefone();
        if(dadosAtualizarPaciente.endereco() != null) this.endereco = new Endereco(dadosAtualizarPaciente.endereco());
    }

    public void excluir(){
        this.ativo = false;
    }
}
