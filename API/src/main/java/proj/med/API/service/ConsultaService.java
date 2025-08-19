package proj.med.API.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import proj.med.API.Interfaces.ConsultaInterface;
import proj.med.API.dto.DadosAgendamentoConsulta;
import proj.med.API.enums.MotivoCancelamentoConsulta;
import proj.med.API.exceptions.ValidacaoException;
import proj.med.API.model.Consulta;
import proj.med.API.model.Medico;
import proj.med.API.model.Paciente;
import proj.med.API.repository.ConsultaRepository;
import proj.med.API.repository.MedicoRepository;
import proj.med.API.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ConsultaInterface> listaDeConsulta;

    @Transactional
    public void agendarConsulta(DadosAgendamentoConsulta dados) {
        LocalDateTime dataHora = dados.dataHora();

        Medico medico = buscarOuSelecionarMedico(dados, dataHora);

        listaDeConsulta.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.findById(dados.idPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente nao encontrado no sistema!"));

        if (!paciente.getAtivo()) {
            throw new ValidacaoException("Paciente inativo no sistema!");
        }

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHoraConsulta(dataHora);
        consultaRepository.save(consulta);

    }

    @Transactional
    public void cancelarConsulta(Long idConsulta, MotivoCancelamentoConsulta motivoCancelamentoConsulta) {

        Consulta consulta = consultaRepository.getReferenceById(idConsulta);
        LocalDateTime agora = LocalDateTime.now();

        if (consulta.getDataHoraConsulta().isBefore(agora.plusHours(24))) {
            throw new ValidacaoException("Consulta só pode ser cancelada com no mínimo 24h de antecedência");
        }

        consulta.setCancelada(true);
        consulta.setMotivoCancelamentoConsulta(motivoCancelamentoConsulta);
    }

    private Medico buscarOuSelecionarMedico(DadosAgendamentoConsulta dados, LocalDateTime dataHora) {
        if (dados.idMedico() != null) {
            Medico medico = medicoRepository.findById(dados.idMedico())
                    .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

            if (!medico.getAtivo()) {
                throw new ValidacaoException("Médico inativo.");
            }

            LocalDateTime inicio = dataHora;
            LocalDateTime fim = dataHora.plusHours(1);

            boolean medicoOcupado = consultaRepository.existsByMedicoAndHorarioSobreposto(dados.idMedico(), inicio,
                    fim) == 1;
            if (medicoOcupado) {
                throw new ValidacaoException("Médico já possui outra consulta neste horário.");
            }

            return medico;
        } else {
            List<Medico> disponiveis = medicoRepository.findMedicosDisponiveis(dataHora);
            if (disponiveis.isEmpty()) {
                throw new ValidacaoException("Nenhum médico disponível neste horário.");
            }
            return disponiveis.get(new Random().nextInt(disponiveis.size()));
        }
    }
}
