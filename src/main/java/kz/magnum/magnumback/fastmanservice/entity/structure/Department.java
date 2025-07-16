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
@Table(name = "departments", schema = "structure")
public class Department implements Serializable {
    private static final long serialVersionUID = -3662417069276069481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Long id;
    @Column(name = "code_department")
    private Long codeDepartment;
    @Column(name = "name_department_short")
    private String shortNameDepartment;
    @Column(name = "name_department_long")
    private String longNameDepartment;
    @Column(name = "str_level_department")
    private Short strLevelDepartment;
    @Column(name = "department_code_sprut")
    private String codeDepartmentSprut;
    @Column(name = "value_reception_department")
    private Double valueReceptionDepartment;
    @Column(name = "value_critical_department")
    private Double valueCriticalDepartment;
    @Column(name = "active_department")
    private boolean isActiveDepartment;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_code_direction", referencedColumnName = "code_direction")
    private Direction direction;
    @OneToMany(mappedBy = "department")
    private List<Group> groups;
}