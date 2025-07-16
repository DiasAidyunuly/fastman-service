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
@Table(name = "CLIADRES", schema = "CEN510MGN")
public class Cliadres {
    @Id
    @Column(name = "ADRNCLI", nullable = false)
    private String adrNcli;

    @Column(name = "ADRADRE", nullable = false)
    private Integer adrAdre;

    @Column(name = "ADRLFON")
    private String adrLfon;

    @Column(name = "ADRRAIS", nullable = false)
    private String adrRais;

    @Column(name = "ADRRUE1")
    private String adrRue1;

    @Column(name = "ADRRUE2")
    private String adrRue2;

    @Column(name = "ADRVILL")
    private String adrVill;

    @Column(name = "ADRCODE")
    private String adrCode;

    @Column(name = "ADRPAYS", nullable = false)
    private Integer adrPays;

    @Column(name = "ADRDIST")
    private String adrDist;

    @Column(name = "ADRREGN")
    private String adrRegn;

    @Column(name = "ADRDCRE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date adrDcre;

    @Column(name = "ADRDMAJ", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date adrDmaj;

    @Column(name = "ADRUTIL", nullable = false)
    private String adrUtil;

    @Column(name = "ADRNMOD", nullable = false)
    private Integer adrNmod;

    @Column(name = "ADRCOMM")
    private String adrComm;

    @Column(name = "ADRACCI")
    private String adrAcci;

    @Column(name = "ADRBUDCODE")
    private String adrBudcode;

    @Column(name = "ADRIDEN")
    private String adrIden;

    @Column(name = "ADRLATORT")
    private Integer adrLatOrt;

    @Column(name = "ADRLATDEG")
    private Integer adrLatDeg;

    @Column(name = "ADRLATMIN")
    private Integer adrLatMin;

    @Column(name = "ADRLATSEC", precision = 5, scale = 3)
    private Double adrLatSec;

    @Column(name = "ADRLONORT")
    private Integer adrLonOrt;

    @Column(name = "ADRLONDEG")
    private Integer adrLonDeg;

    @Column(name = "ADRLONMIN")
    private Integer adrLonMin;

    @Column(name = "ADRLONSEC", precision = 5, scale = 3)
    private Double adrLonSec;

    @Column(name = "ADRLATDD", precision = 9, scale = 7)
    private Double adrLatDd;

    @Column(name = "ADRLONDD", precision = 10, scale = 7)
    private Double adrLonDd;
}
