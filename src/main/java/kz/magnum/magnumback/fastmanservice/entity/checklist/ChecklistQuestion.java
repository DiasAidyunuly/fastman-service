package kz.magnum.magnumback.fastmanservice.entity.checklist;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "checklists_questions", schema = "checklist")
public class ChecklistQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questions_task", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_head")
    private ChecklistHead checklistHead;
    @ManyToOne
    @JoinColumn(name = "id_question_status")
    private ChecklistQuestionStatus checklistQuestionStatus;
    @ManyToOne
    @JoinColumn(name = "id_question_temp")
    private ChecklistQuestionTemp checklistQuestionTemp;
    @Column(name = "answer_question")
    private String questionAnswer;
    @Column(name = "comment")
    private String comment;
    @Column(name = "weight_completion")
    private Short weightCompletion;
    @Column(name = "photo_completion")
    private String photoCompletion;
}