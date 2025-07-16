package kz.magnum.magnumback.fastmanservice.repository.gold;

import kz.magnum.magnumback.fastmanservice.entity.gold.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, String> {
    @Query("SELECT DISTINCT s.clicode2 FROM Site s WHERE s.clilibl LIKE 'ТОРГОВЫЙ ЗАЛ%' AND s.cliclcl NOT IN (5, 6)")
    List<String> findAllCodeFils();

    @Query("SELECT s.clincli FROM Site s WHERE s.clilibl LIKE 'ТОРГОВЫЙ ЗАЛ%' AND CAST(s.clicode2 AS integer) = :codeFil")
    String findCodeSiteTz(@Param("codeFil") Integer codeFil);

    @Query("SELECT s.clilibl FROM Site s WHERE CAST(s.clincli AS integer) = :codeSiteTz")
    String findNameSiteTz(@Param("codeSiteTz") Integer codeSiteTz);

    @Query("SELECT s.clincli FROM Site s WHERE s.clilibl LIKE 'ПРОИЗВОДСТВО%' AND CAST(s.clicode2 AS integer) = :codeFil")
    String findCodeSiteSp(@Param("codeFil") Integer codeFil);

    @Query("SELECT s.clilibl FROM Site s WHERE CAST(s.clincli AS integer) = :codeSiteSp")
    String findNameSiteSp(@Param("codeSiteSp") Integer codeSiteSp);
}
