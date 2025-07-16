package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(StructureId.class)
@Table(name = "STRUCTURE", schema = "CEN510MGN")
public class Structure {
    @Id
    @Column(name = "STRCINT")
    private Long strcint;
    @Id
    @Column(name = "STRPERE")
    private Long strpere;
    @Id
    @Column(name = "STRPROF")
    private Short strprof;
}