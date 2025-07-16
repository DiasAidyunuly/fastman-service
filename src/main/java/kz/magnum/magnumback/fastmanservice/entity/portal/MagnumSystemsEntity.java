package kz.magnum.magnumback.fastmanservice.entity.portal;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(schema = "nsi", name = "magnum_systems")
public class MagnumSystemsEntity {

    @Id
    @Column(name = "system_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemId;
    @Column(name = "system_code")
    private String systemCode;
    @Column(name = "system_name_kaz")
    private String systemNameKaz;
    @Column(name = "system_name_rus")
    private String systemNameRus;
    @Column(name = "is_arc")
    private Short isArc;
    @OneToMany(mappedBy = "magnumSystemsEntity", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<MagnumSystemModulesEntity> magnumSystemModulesEntities;
}
