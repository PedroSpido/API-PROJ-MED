package proj.med.API.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import proj.med.API.dto.DadosAtualizarMedico;
import proj.med.API.dto.DadosCadastroMedicoDTO;
import proj.med.API.enums.Especialidade;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private int crm;
    private Especialidade especialidade;
    private Boolean ativo = true;

    @Embedded
    private Endereco endereco;

    public Medico() {
    }

    public Medico(DadosCadastroMedicoDTO dadosCadastroMedico){
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
    }

    public void atualizarInfosMedico(DadosAtualizarMedico dadosAtualizarMedico){
        if(dadosAtualizarMedico.email() != null || dadosAtualizarMedico.crm() != null || dadosAtualizarMedico.especialidade() != null){
            throw new IllegalArgumentException("Nao eh possivel atualizar crm, email ou especialidade");
        }
        if(dadosAtualizarMedico.nome() != null) this.nome = dadosAtualizarMedico.nome();
        if(dadosAtualizarMedico.telefone() != null) this.telefone = dadosAtualizarMedico.telefone();
        if(dadosAtualizarMedico.endereco() != null) this.endereco = new Endereco(dadosAtualizarMedico.endereco());
    }

    public void excluir(){
        this.ativo = false;
    }
}
