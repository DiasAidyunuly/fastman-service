package kz.magnum.magnumback.fastmanservice.entity.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subgroups", schema = "structure")
public class Subgroup implements Serializable {
    private static final long serialVersionUID = -7253272964591492811L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subgroup")
    private Long id;
    @Column(name = "code_subgroup")
    private Long codeSubgroup;
    @Column(name = "name_subgroup_short")
    private String shortNameSubgroup;
    @Column(name = "name_subgroup_long")
    private String longNameSubgroup;
    @Column(name = "str_level_subgroup")
    private Short strLevelSubgroup;
    @Column(name = "subgroup_code_sprut")
    private String codeSubgroupSprut;
    @Column(name = "value_reception_subgroup")
    private Double valueReceptionSubgroup;
    @Column(name = "value_critical_subgroup")
    private Double valueCriticalSubgroup;
    @Column(name = "active_subgroup")
    private boolean isActiveSubgroup;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_code_group", referencedColumnName = "code_group")
    private Group group;
}