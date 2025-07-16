package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STRUCOBJ", schema = "CEN510MGN")
public class Strucobj {
    @Id
    @Column(name = "SOBCINT")
    private Long sobcint;
    @Column(name = "SOBCEXT")
    private String sobcext;
    @Column(name = "SOBCEXTIN")
    private String sobcextin;
    @Column(name = "SOBIDNIV")
    private Short sobidniv;
}