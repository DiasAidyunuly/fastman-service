package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "site_exceptions", schema = "fastman")
public class SiteException {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_exception_id", nullable = false)
    private Long id;
    @Column(name = "exc_qty_task")
    private Integer excQtyTask;
    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;
    @Column(name = "location_id")
    private Short locationId;
    @Column(name = "site_name")
    private String siteName;
}
