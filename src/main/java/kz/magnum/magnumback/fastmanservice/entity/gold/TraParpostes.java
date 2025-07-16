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
@IdClass(TraParpostesId.class)
@Table(name = "TRA_PARPOSTES", schema = "CEN510MGN")
public class TraParpostes {
    @Id
    @Column(name = "TPARTABL", nullable = false)
    private Integer tpartabl;

    @Id
    @Column(name = "TPARPOST", nullable = false)
    private Integer tparpost;

    @Id
    @Column(name = "TPARCMAG", nullable = false)
    private Integer tparcmag;

    @Id
    @Column(name = "LANGUE", nullable = false)
    private String langue;

    @Column(name = "TPARLIBC", nullable = false)
    private String tparlibc;

    @Column(name = "TPARLIBL", nullable = false)
    private String tparlibl;

    @Column(name = "TPARCOMM")
    private String tparcomm;

    @Column(name = "TPARUTIL", nullable = false)
    private String tparutil;

    @Column(name = "TPARDMAJ", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tpardmaj;

    @Column(name = "TPARDCRE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tpardcre;

    @Column(name = "TPARNMOD", nullable = false)
    private Integer tparnmod;

    @Column(name = "DTRADUCTION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtraduction;

    @Column(name = "ETRADUCTION", nullable = false)
    private Integer etraduction;
}
