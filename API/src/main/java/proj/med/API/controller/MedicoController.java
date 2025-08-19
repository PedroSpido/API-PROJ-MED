package proj.med.API.controller;

import java.net.URI;

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
import proj.med.API.dto.DadosAtualizarMedico;
import proj.med.API.dto.DadosCadastroMedicoDTO;
import proj.med.API.dto.DadosListagemMedicoDto;
import proj.med.API.dto.DadosMedicoCadastradoOuAtualizado;
import proj.med.API.model.Medico;
import proj.med.API.repository.MedicoRepository;
import proj.med.API.service.MedicoService;

@RestController
@RequestMapping("/medicos")
@Validated
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    private MedicoService service;

    @Autowired
    public MedicoController(MedicoService service){
        this.service = service;
    }

    //EndPoint para mostrar o medico conforme o id sugerido
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> findMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosMedicoCadastradoOuAtualizado(medico));
    }

    @PostMapping("/cadastrarMedico")
    @Transactional
    public ResponseEntity<DadosMedicoCadastradoOuAtualizado> cadastroMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriComponentsBuilder){
        Medico medico = new Medico(dados);
        medicoRepository.save(medico);

        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosMedicoCadastradoOuAtualizado(medico));
    }

    @GetMapping("/listarMedicos")
    public ResponseEntity<Page<DadosListagemMedicoDto>> listarMedicos(Pageable pageable){
        var lista = service.listaMedicos(pageable);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/atualizarMedico")
    @Transactional
    public ResponseEntity<DadosMedicoCadastradoOuAtualizado> atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInfosMedico(dados);

        return ResponseEntity.ok(new DadosMedicoCadastradoOuAtualizado(medico));
    }

    @DeleteMapping("/deletarMedico/{id}")
    @Transactional
    public ResponseEntity<String> deletaMedicos(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.ok("Medico deletado com sucesso!");
    }
}
