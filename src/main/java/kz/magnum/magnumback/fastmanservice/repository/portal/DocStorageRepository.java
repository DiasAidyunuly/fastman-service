package kz.magnum.magnumback.fastmanservice.repository.portal;

import kz.magnum.magnumback.fastmanservice.entity.portal.DocStorageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocStorageRepository extends CrudRepository<DocStorageEntity, Long> {
    @Query("select dc from DocStorageEntity dc where dc.docId = :docId")
    Optional<DocStorageEntity> findByDocUid(UUID docId);
}
