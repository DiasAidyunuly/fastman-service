package kz.magnum.magnumback.fastmanservice.repository.gold;

import kz.magnum.magnumback.fastmanservice.entity.gold.Strucobj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StrucobjRepository extends JpaRepository<Strucobj, Long> {
    @Query(value =
        "select " +
            "st.sobcint, " +
            "st.sobcext, " +
            "tst.tsobdesc, " +
            "st.sobcextin " +
        "from STRUCOBJ st " +
        "join TRA_STRUCOBJ tst " +
            "on st.sobcint = tst.tsobcint " +
            "and tst.langue = 'RU' " +
            "and tst.tsobdesc not like ('УДАЛ%') " +
            "and tst.tsobdesc not like ('ТЕСТ%') " +
        "where st.sobidniv = 1",
        nativeQuery = true)
    List<Object[]> findDataMapToHeadStructure();

    @Query(value =
        "select " +
            "st.sobcint, " +
            "st.sobcext, " +
            "tst.tsobdesc, " +
            "str.strpere, " +
            "st.sobcextin " +
        "from STRUCOBJ st " +
        "join TRA_STRUCOBJ tst " +
            "on st.sobcint = tst.tsobcint " +
            "and tst.langue = 'RU' " +
            "and tst.tsobdesc not like ('УДАЛ%') " +
            "and tst.tsobdesc not like ('ТЕСТ%') " +
        "join STRUCTURE str " +
            "on st.sobcint = str.strcint " +
            "and str.strprof = st.sobidniv - 1 " +
        "where st.sobidniv = 2",
        nativeQuery = true)
    List<Object[]> findDataMapToDirection();

    @Query(value =
        "select " +
            "st.sobcint, " +
            "st.sobcext, " +
            "tst.tsobdesc, " +
            "str.strpere, " +
            "st.sobcextin " +
        "from STRUCOBJ st " +
        "join TRA_STRUCOBJ tst " +
            "on st.sobcint = tst.tsobcint " +
            "and tst.langue = 'RU' " +
            "and tst.tsobdesc not like ('УДАЛ%') " +
            "and tst.tsobdesc not like ('ТЕСТ%') " +
        "join STRUCTURE str " +
            "on st.sobcint = str.strcint " +
            "and str.strprof = st.sobidniv - 1 " +
        "join strucrel stl " +
            "on st.sobcint = stl.objcint " +
            "and sysdate between stl.objddeb and stl.objdfin " +
        "where st.sobidniv = 3",
        nativeQuery = true)
    List<Object[]> findDataMapToDepartment();

    @Query(value =
        "select " +
            "st.sobcint, " +
            "st.sobcext, " +
            "tst.tsobdesc, " +
            "str.strpere, " +
            "st.sobcextin " +
        "from strucobj st " +
        "join tra_strucobj tst " +
            "on st.sobcint = tst.tsobcint " +
            "and tst.langue = 'RU' " +
            "and tst.tsobdesc not like ('УДАЛ%') " +
            "and tst.tsobdesc not like ('ТЕСТ%') " +
        "join structure str " +
            "on st.sobcint = str.strcint " +
            "and str.strprof = st.sobidniv - 1 " +
        "join strucrel stl " +
            "on st.sobcint = stl.objcint " +
            "and sysdate between stl.objddeb and stl.objdfin " +
        "where st.sobidniv = 4",
        nativeQuery = true)
    List<Object[]> findDataMapToGroup();

    @Query(value =
        "select " +
            "st.sobcint, " +
            "st.sobcext, " +
            "tst.tsobdesc, " +
            "str.strpere, " +
            "st.sobcextin " +
        "from strucobj st " +
        "join tra_strucobj tst " +
            "on st.sobcint = tst.tsobcint " +
            "and tst.langue = 'RU' " +
            "and tst.tsobdesc not like ('УДАЛ%') " +
            "and tst.tsobdesc not like ('ТЕСТ%') " +
        "join structure str " +
            "on st.sobcint = str.strcint " +
            "and str.strprof = st.sobidniv - 1 " +
        "join strucrel stl " +
            "on st.sobcint = stl.objcint " +
            "and sysdate between stl.objddeb and stl.objdfin " +
        "where st.sobidniv = 5",
    nativeQuery = true)
    List<Object[]> findDataMapToSubGroup();
}