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
@Table(name = "checklist_head_temps", schema = "checklist")
public class ChecklistHeadTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_head_temp", nullable = false)
    private Long id;
    @Column(name = "name_head")
    private String headName;
    @ManyToOne
    @JoinColumn(name = "id_template")
    private ChecklistTemplate checklistTemplate;
    @Column(name = "period_for_manual_tasks")
    private Short periodForManualTasks;
    @Column(name = "only_the_creator")
    private boolean isOnlyTheCreator;
    @Column(name = "need_planning")
    private boolean isNeedPlanning;
    @Column(name = "photo_gallery")
    private boolean isPhotoGallery;
    @Column(name = "role_performer")
    private String rolePerformer;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistHeadTemp")
    private List<ChecklistChapter> checklistChapters;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistHeadTemp")
    private List<ChecklistHead> checklistHeads;
}