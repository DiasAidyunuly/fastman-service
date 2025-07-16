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
@Table(name = "directions", schema = "structure")
public class Direction implements Serializable {
    private static final long serialVersionUID = -7749372498563435661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction")
    private Long id;
    @Column(name = "code_direction")
    private Long codeDirection;
    @Column(name = "name_direction_short")
    private String shortNameDirection;
    @Column(name = "name_direction_long")
    private String longNameDirection;
    @Column(name = "str_level_direction")
    private Short strLevelDirection;
    @Column(name = "direction_code_sprut")
    private String codeDirectionSprut;
    @Column(name = "value_reception_direction")
    private Double valueReceptionDirection;
    @Column(name = "value_critical_direction")
    private Double valueCriticalDirection;
    @Column(name = "active_direction")
    private boolean isActiveDirection;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_code_head", referencedColumnName = "code_head_structure")
    private HeadStructure headStructure;
    @OneToMany(mappedBy = "direction")
    private List<Department> departments;
}