package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "execution_tasks", schema = "fastman")
public class ExecutionTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_task_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fastman_task_id")
    private FastmanTask fastmanTask;
    @ManyToOne
    @JoinColumn(name = "execution_id")
    private Execution execution;
    @Column(name = "execution_value")
    private String executionValue;
}