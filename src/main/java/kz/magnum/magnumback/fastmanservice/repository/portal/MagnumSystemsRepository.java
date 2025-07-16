package kz.magnum.magnumback.fastmanservice.repository.portal;

import kz.magnum.magnumback.fastmanservice.entity.portal.MagnumSystemsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagnumSystemsRepository extends CrudRepository<MagnumSystemsEntity, Long> {
}
