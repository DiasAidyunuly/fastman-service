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
@Table(name = "checklists_head_statuses", schema = "checklist")
public class ChecklistHeadStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_head_status")
    private Long id;
    @Column(name = "name_head_status")
    private String headStatusName;
    @JsonIgnore
    @OneToMany(mappedBy = "checklistHeadStatus")
    private List<ChecklistHead> checklistHeads;
}