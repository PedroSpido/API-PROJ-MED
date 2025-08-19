package proj.med.API.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import proj.med.API.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
