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
@Table(name = "checklists_types_of_answers", schema = "checklist")
public class ChecklistTypeOfAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type", nullable = false)
    private Long id;
    @Column(name = "name_type")
    private String typeName;
    @Column(name = "ui_element_type")
    private Short elementUIType;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistTypeOfAnswer")
    private List<ChecklistQuestionTemp> checklistQuestionTemps;
}
