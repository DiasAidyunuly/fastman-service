package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "actions", schema = "fastman")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id", nullable = false)
    private Short id;
    @Column(name = "action_name")
    private String actionName;
    @Column(name = "completion_with_check")
    private Boolean completionWithCheck;
    @JsonIgnore
    @OneToMany(mappedBy = "action")
    private List<IncAction> incActions;
    @JsonIgnore
    @OneToMany(mappedBy = "action")
    private List<FastmanTask> fastmanTasks;
    @JsonIgnore
    @OneToMany(mappedBy = "action")
    private List<Incident> incidents;
}
