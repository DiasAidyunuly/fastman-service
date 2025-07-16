package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(TraStrucobjId.class)
@Table(name = "TRA_STRUCOBJ", schema = "CEN510MGN")
public class TraStrucobj {
    @Id
    @Column(name = "TSOBCINT")
    private Long tsobcint;
    @Id
    @Column(name = "LANGUE")
    private String language;
    @Column(name = "TSOBDESC")
    private String tsobdesc;
}