package kz.magnum.magnumback.fastmanservice.repository.file;

import kz.magnum.magnumback.fastmanservice.entity.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    Optional<FileEntity> findFirstByFileHash(String fileHash);
}