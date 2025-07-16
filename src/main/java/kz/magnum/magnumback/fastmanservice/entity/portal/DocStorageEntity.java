package kz.magnum.magnumback.fastmanservice.entity.portal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(schema = "doc_storage", name = "doc_storages")
public class DocStorageEntity {

    @Id
    @Column(name = "doc_id")
    private UUID docId = UUID.randomUUID();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private MagnumSystemModulesEntity magnumSystemModulesEntity;
    @Column(name = "doc_extension")
    private String docExtension;
    @Column(name = "doc_size")
    private Float docSize;
    @Column(name = "doc_name")
    private String docName;
    @Column(name = "doc_path")
    private String docPath;
    @Column(name = "doc_url")
    private String docUrl;
    @Column(name = "doc_guid")
    private String docGuid;
    @Column(name="create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}
