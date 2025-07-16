package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executions", schema = "fastman")
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_id", nullable = false)
    private Long id;
    @Column(name = "execution_name")
    private String executionName;
    @JsonIgnore
    @OneToMany(mappedBy = "execution")
    private List<IncExecution> incExecutions;
    @Column(name = "id_of_type")
    private Long typeOfExecutionId;
    @JsonIgnore
    @OneToMany(mappedBy = "execution")
    private List<ExecutionTask> executionTasks;
}