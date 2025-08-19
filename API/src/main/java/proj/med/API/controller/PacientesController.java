package proj.med.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import proj.med.API.dto.DadosAtualizarPaciente;
import proj.med.API.dto.DadosCadastroPaciente;
import proj.med.API.dto.DadosPacienteCadastrado;
import proj.med.API.model.Paciente;
import proj.med.API.repository.PacienteRepository;
import proj.med.API.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
@Validated
@SecurityRequirement(name = "bearer-key")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    private PacienteService service;

    public PacientesController(PacienteService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> findPaciente(@PathVariable Long id){   
        Paciente paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosPacienteCadastrado(paciente));
    }

    @PostMapping("/cadastrarPaciente")
    @Transactional
    public ResponseEntity<DadosPacienteCadastrado> cadastrarPaciente(@Valid @RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosPacienteCadastrado(paciente));
    }

    @DeleteMapping("/deletarPaciente/{id}")
    @Transactional
    public ResponseEntity<String> deletarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.ok("Paciente deletado com sucesso!");
    }

    @GetMapping("/listarPacientes")
    @Transactional
    public Page<Paciente> listarPacientes(Pageable pageable){
        return service.listaPacientes(pageable);
    }

    @PutMapping("/atualizarPaciente")
    @Transactional
    public ResponseEntity<String> atualizarPaciente(@Valid @RequestBody DadosAtualizarPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInfosPaciente(dados);

        return ResponseEntity.ok("Paciente atualizado com sucesso!");
    } 

}
