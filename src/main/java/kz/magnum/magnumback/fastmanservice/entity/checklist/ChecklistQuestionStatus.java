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
@Table(name = "checklists_question_statuses", schema = "checklist")
public class ChecklistQuestionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_status")
    private Long id;
    @Column(name = "name_question_status")
    private String questionStatusName;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistQuestionStatus")
    private List<ChecklistQuestion> checklistQuestions;
}