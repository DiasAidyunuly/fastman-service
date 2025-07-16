package kz.magnum.magnumback.fastmanservice.repository.portal;

import kz.magnum.magnumback.fastmanservice.entity.portal.MagnumSystemModulesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagnumSystemModulesRepository extends CrudRepository<MagnumSystemModulesEntity, Long> {

    @Query("select msm from MagnumSystemModulesEntity msm where msm.moduleCode = :moduleCode")
    Optional<MagnumSystemModulesEntity> findByModuleCode(String moduleCode);
}
