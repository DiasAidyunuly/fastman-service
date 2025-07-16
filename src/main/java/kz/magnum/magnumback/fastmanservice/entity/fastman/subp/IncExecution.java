package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inc_executions", schema = "fastman")
public class IncExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inc_execution_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;
    @ManyToOne
    @JoinColumn(name = "execution_id")
    private Execution execution;
    @Column(name = "mandatory")
    private Byte mandatory;
    @Column(name = "allow_check")
    private Byte allowCheck;
}
