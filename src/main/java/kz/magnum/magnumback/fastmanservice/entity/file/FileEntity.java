package kz.magnum.magnumback.fastmanservice.entity.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "file", name = "files")
public class FileEntity {
    @Id
    @Column(name = "file_id")
    private UUID fileId;
    @Column(name = "file_name")
    private String fileNameWithoutExtension;
    @Column(name = "file_extension")
    private String fileExtension;
    @Column(name = "directory")
    private String directoryPath;
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;
    @Column(name = "file_hash")
    private String fileHash;
}