package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.general.Filial;
import kz.magnum.magnumback.fastmanservice.model.SiteWithCodeAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilialRepository extends JpaRepository<Filial, Integer> {
    @Query("SELECT new kz.magnum.magnumback.fastmanservice.model.SiteWithCodeAndName(f.codeSiteSp, f.nameSiteSp) FROM Filial f")
    List<SiteWithCodeAndName> findCodeSiteSpAndNameSiteSp();

    @Query("SELECT new kz.magnum.magnumback.fastmanservice.model.SiteWithCodeAndName(f.codeSiteTz, f.nameSiteTz) FROM Filial f")
    List<SiteWithCodeAndName> findCodeSiteTzAndNameSiteTz();
}
