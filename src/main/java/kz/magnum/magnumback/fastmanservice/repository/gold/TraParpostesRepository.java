package kz.magnum.magnumback.fastmanservice.repository.gold;

import kz.magnum.magnumback.fastmanservice.entity.gold.TraParpostes;
import kz.magnum.magnumback.fastmanservice.entity.gold.TraParpostesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TraParpostesRepository extends JpaRepository<TraParpostes, TraParpostesId> {
    @Query("SELECT t.tparlibl FROM TraParpostes t WHERE t.tpartabl = 721 " +
            "AND t.langue = 'RU' AND t.tparcmag = 10 " +
            "AND t.tparpost = (SELECT s.cliclcl FROM Site s WHERE s.clilibl LIKE 'ТОРГОВЫЙ ЗАЛ%' " +
            "AND CAST(s.clicode2 AS integer) = :codeFil)")
    String findFormat(@Param("codeFil") Integer codeFil);
}
