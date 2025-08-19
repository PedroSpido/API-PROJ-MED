package proj.med.API.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import proj.med.API.model.Paciente;
import proj.med.API.repository.PacienteRepository;

@Service
public class PacienteService {

    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Page<Paciente> listaPacientes(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
