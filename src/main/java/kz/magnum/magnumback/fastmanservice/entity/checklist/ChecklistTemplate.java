package kz.magnum.magnumback.fastmanservice.entity.checklist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "checklists_templates", schema = "checklist")
public class ChecklistTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_template", nullable = false)
    private Long id;
    @Column(name = "name_template")
    private String templateName;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistTemplate")
    private List<ChecklistHeadTemp> checklistHeadTemps;
}
