package kz.magnum.magnumback.fastmanservice.entity.portal;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(schema = "nsi", name = "magnum_system_modules")
public class MagnumSystemModulesEntity {

    @Id
    @Column(name = "module_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "system_id", nullable = false)
    private MagnumSystemsEntity magnumSystemsEntity;
    @Column(name = "module_code")
    private String moduleCode;
    @Column(name = "module_name_kaz")
    private String moduleNameKaz;
    @Column(name = "module_name_rus")
    private String moduleNameRus;
    @Column(name = "is_arc")
    private Short isArc;
    @OneToMany(mappedBy = "magnumSystemModulesEntity", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<DocStorageEntity> docStorageEntities;
}
