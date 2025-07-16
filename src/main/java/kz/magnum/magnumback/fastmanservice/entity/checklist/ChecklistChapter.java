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
@Table(name = "checklists_chapter", schema = "checklist")
public class ChecklistChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chapter", nullable = false)
    private Long id;
    @Column(name = "name_chapter")
    private String chapterName;
    @Column(name = "is_grouped")
    private Boolean isGrouped;
    @ManyToOne
    @JoinColumn(name = "id_head_temp")
    private ChecklistHeadTemp checklistHeadTemp;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistChapter")
    private List<ChecklistQuestionTemp> checklistQuestionTemps;
}