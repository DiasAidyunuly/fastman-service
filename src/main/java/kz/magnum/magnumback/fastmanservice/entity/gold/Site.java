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
@Table(name = "CLIDGENE", schema = "CEN510MGN")
public class Site {
    @Id
    @Column(name = "CLINCLI", nullable = false)
    private String clincli;

    @Column(name = "CLITYMA", nullable = false)
    private Integer clityma;

    @Column(name = "CLILIBL", nullable = false)
    private String clilibl;

    @Column(name = "CLISTAT", nullable = false)
    private Integer clistat;

    @Column(name = "CLIENSE", nullable = false)
    private Integer cliensE;

    @Column(name = "CLICLCL", nullable = false)
    private Integer cliclcl;

    @Column(name = "CLIPOSM")
    private Integer cliposm;

    @Column(name = "CLIRESP")
    private String cliresp;

    @Column(name = "CLIRELIC", nullable = false)
    private Integer clirelic;

    @Column(name = "CLIPRIL", nullable = false)
    private Integer clipril;

    @Column(name = "CLIBCDE")
    @Temporal(TemporalType.DATE)
    private Date clibcde;

    @Column(name = "CLIBCFI")
    @Temporal(TemporalType.DATE)
    private Date clibcfi;

    @Column(name = "CLIBLDE")
    @Temporal(TemporalType.DATE)
    private Date cliblde;

    @Column(name = "CLIBLFI")
    @Temporal(TemporalType.DATE)
    private Date cliblfi;

    @Column(name = "CLISURA")
    private Integer clisura;

    @Column(name = "CLIPEMO")
    private String clipemo;

    @Column(name = "CLICENT")
    private Integer clicent;

    @Column(name = "CLILANG", nullable = false)
    private Integer clilang;

    @Column(name = "CLIDEVI", nullable = false)
    private Integer clidevi;

    @Column(name = "CLILICA", nullable = false)
    private Integer clilica;

    @Column(name = "CLIMOCO")
    private Integer climoco;

    @Column(name = "CLIINFO", nullable = false)
    private Integer cliinfo;

    @Column(name = "CLIRELE", nullable = false)
    private Integer clirele;

    @Column(name = "CLITAXE", nullable = false)
    private Integer clitaxe;

    @Column(name = "CLIPOSA", nullable = false)
    private Integer cliposa;

    @Column(name = "CLITVA", nullable = false)
    private Integer clitva;

    @Column(name = "CLIRTVA", nullable = false)
    private Integer clirtva;

    @Column(name = "CLINTOU")
    private String clintou;

    @Column(name = "CLIPERE")
    private String clipere;

    @Column(name = "CLICODE1")
    private String clicode1;

    @Column(name = "CLICODE2")
    private String clicode2;

    @Column(name = "CLICOMM")
    private String clicomm;

    @Column(name = "CLIDCRE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date clidcre;

    @Column(name = "CLIDMAJ", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date clidmaj;

    @Column(name = "CLIUTIL", nullable = false)
    private String cliutil;

    @Column(name = "CLINMOD", nullable = false)
    private Integer clinmod;

    @Column(name = "CLIMFAC", nullable = false)
    private Integer climfac;

    @Column(name = "CLISOCJU")
    private Integer clisocju;

    @Column(name = "CLIINST")
    private Integer cliinst;

    @Column(name = "CLIOUVCP")
    private String cliouvcp;

    @Column(name = "CLISFV")
    private Integer clisfv;

    @Column(name = "CLISURU")
    private Integer clisuru;
}
