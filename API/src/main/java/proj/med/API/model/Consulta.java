package proj.med.API.model;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proj.med.API.enums.MotivoCancelamentoConsulta;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "medico_id")
    private Medico medico;

    private boolean cancelada = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento_consulta")
    private MotivoCancelamentoConsulta motivoCancelamentoConsulta;

    @ManyToOne
    @JoinColumn(name= "paciente_id")
    private Paciente paciente;

    @Column(name = "data")
    private LocalDateTime dataHoraConsulta;
}
