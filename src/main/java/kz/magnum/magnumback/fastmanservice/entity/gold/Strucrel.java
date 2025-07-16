package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(StrucrelId.class)
@Table(name = "STRUCREL", schema = "CEN510MGN")
public class Strucrel {
    @Id
    @Column(name = "OBJCINT")
    private Long objcint;
    @Id
    @Column(name = "OBJPERE")
    private Long objpere;
    @Id
    @Column(name = "OBJDDEB")
    private Date objddeb;
    @Column(name = "OBJDFIN")
    private Date objdfin;
}