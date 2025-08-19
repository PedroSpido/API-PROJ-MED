package proj.med.API.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import proj.med.API.dto.DadosListagemMedicoDto;
import proj.med.API.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

    @Query("""
        SELECT m FROM Medico m
        WHERE m.ativo = true 
        AND m.id NOT IN (
            SELECT c.medico.id FROM Consulta c 
            WHERE c.dataHoraConsulta = :dataHora
        )
    """)
    List<Medico> findMedicosDisponiveis(@Param("dataHora") LocalDateTime dataHora);
    Page<DadosListagemMedicoDto> findAllByAtivoTrue(Pageable pageable);
}
