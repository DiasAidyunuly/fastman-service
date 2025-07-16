package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "types_of_executions", schema = "fastman")
public class TypeOfExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_of_type", nullable = false)
    private Long id;
    @Column(name = "name_of_type")
    private String nameOfType;
}