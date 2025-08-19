package proj.med.API.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import proj.med.API.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndDataHoraConsultaBetween(Long idPaciente, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByMedicoIdAndDataHoraConsulta(Long idMedico, LocalDateTime dataHoraConsulta);

    //Verifica se ja existe consulta sobreposta naquele horario***
    @Query(value = """
                SELECT COUNT(*) > 0
                FROM consulta c
                WHERE c.medico_id = :medicoId
                  AND c.data < :fim
                  AND DATE_ADD(c.data, INTERVAL 1 HOUR) > :inicio
            """, nativeQuery = true) 
    int existsByMedicoAndHorarioSobreposto(@Param("medicoId") Long medicoId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

}
