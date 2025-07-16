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
@Table(name = "checklist_question_temps", schema = "checklist")
public class ChecklistQuestionTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_temp")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_chapter")
    private ChecklistChapter checklistChapter;
    @ManyToOne
    @JoinColumn(name = "id_type")
    private ChecklistTypeOfAnswer checklistTypeOfAnswer;
    @Column(name = "name_question")
    private String questionName;
    @Column(name = "photo_resolution")
    private boolean photoResolution;
    @Column(name = "weight")
    private Short weight;
    @Column(name = "photo_mandatory")
    private boolean photoMandatory;
    @Column(name = "photo_maximum")
    private Short photoMaximum;
    @Column(name = "comment_resolution")
    private boolean commentResolution;
    @Column(name = "photo_create")
    private String photoCreate;
    @Column(name = "file_create")
    private String fileCreate;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistQuestionTemp")
    private List<ChecklistQuestion> checklistQuestions;
}