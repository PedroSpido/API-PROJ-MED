package proj.med.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import proj.med.API.dto.DadosAgendamentoConsulta;
import proj.med.API.dto.DadosCancelamentoConsultaDto;
import proj.med.API.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService; 

    @PostMapping("/agendarConsulta")
    @Transactional
    public ResponseEntity<String> AgendamentoConsulta(@RequestBody DadosAgendamentoConsulta dadosAgendamentoConsulta){
        consultaService.agendarConsulta(dadosAgendamentoConsulta);
        return ResponseEntity.ok("Consulta agendada com sucesso!");
    }

    @DeleteMapping("/cancelarConsulta")
    @Transactional
    public ResponseEntity<String> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsultaDto dados){
        consultaService.cancelarConsulta(dados.idConsulta(), dados.motivoCancelamentoConsulta());
        return ResponseEntity.ok("Consulta cancelada com sucesso!");
    }
}
