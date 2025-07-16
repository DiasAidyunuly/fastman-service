package kz.magnum.magnumback.fastmanservice.entity.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "groups", schema = "structure")
public class Group implements Serializable {
    private static final long serialVersionUID = -6102316746890607194L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long id;
    @Column(name = "code_group")
    private Long codeGroup;
    @Column(name = "name_group_short")
    private String shortNameGroup;
    @Column(name = "name_group_long")
    private String longNameGroup;
    @Column(name = "str_level_group")
    private Short strLevelGroup;
    @Column(name = "group_code_sprut")
    private String codeGroupSprut;
    @Column(name = "value_reception_group")
    private Double valueReceptionGroup;
    @Column(name = "value_critical_group")
    private Double valueCriticalGroup;
    @Column(name = "active_group")
    private boolean isActiveGroup;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_code_department", referencedColumnName = "code_department")
    private Department department;
    @OneToMany(mappedBy = "group")
    private List<Subgroup> subgroups;
}