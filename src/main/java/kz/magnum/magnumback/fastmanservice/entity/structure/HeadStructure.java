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
@Table(name = "head_structures", schema = "structure")
public class HeadStructure implements Serializable {
    private static final long serialVersionUID = -3570640412601954574L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_head_struсture")
    private Long id;
    @Column(name = "code_head_structure")
    private Long codeHeadStructure;
    @Column(name = "name_head_struсture_short")
    private String shortNameHeadStructure;
    @Column(name = "name_head_struсture_long")
    private String longNameHeadStructure;
    @Column(name = "str_level_head")
    private Short strLevelHead;
    @Column(name = "head_code_sprut")
    private String codeHeadSprut;
    @JsonIgnore
    @OneToMany(mappedBy = "headStructure")
    private List<Direction> directions;
}