package kz.magnum.magnumback.fastmanservice.repository.gold;

import kz.magnum.magnumback.fastmanservice.entity.gold.Cliadres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CliadresRepository extends JpaRepository<Cliadres, String> {
    @Query("SELECT SUBSTRING(c.adrRais, 4) FROM Cliadres c WHERE CAST(c.adrNcli AS integer) = :codeSiteTz")
    String findNameFil(@Param("codeSiteTz") Integer codeSiteTz);

    @Query("SELECT c.adrVill FROM Cliadres c WHERE CAST(c.adrNcli AS integer) = :codeSiteTz")
    String findCity(@Param("codeSiteTz") Integer codeSiteTz);

    @Query("SELECT c.adrComm FROM Cliadres c WHERE CAST(c.adrNcli AS integer) = :codeSiteTz")
    String findAddressFil(@Param("codeSiteTz") Integer codeSiteTz);
}
