package kz.magnum.magnumback.fastmanservice.entity.checklist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "checklists_head", schema = "checklist")
public class ChecklistHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_head")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_head_status")
    private ChecklistHeadStatus checklistHeadStatus;
    @ManyToOne
    @JoinColumn(name = "id_head_temp")
    private ChecklistHeadTemp checklistHeadTemp;
    @Column(name = "site")
    private Integer site;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "start_end_date")
    private Date startEndDate;
    @Column(name = "role_creator")
    private String roleCreator;
    @Column(name = "name_creator")
    private String nameCreator;
    @Column(name = "tab_num_creator")
    private String tabNumCreator;
    @Column(name = "comment_creator")
    private String commentCreator;
    @Column(name = "qty_questions_total")
    private Integer qtyQuestionsTotal;
    @Column(name = "qty_questions_done")
    private Integer qtyQuestionsDone;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistHead")
    private List<ChecklistQuestion> checklistQuestions;
}