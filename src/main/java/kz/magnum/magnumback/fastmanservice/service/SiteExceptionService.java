package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import kz.magnum.magnumback.fastmanservice.model.NewSiteException;

import java.util.List;

public interface SiteExceptionService {
    List<NewSiteException> findAllNewSites();

    List<SiteException> findAll();

    SiteException save(SiteException siteException);

    void delete(Long id);

    SiteException update(Long id, Integer excQtyTask);

    SiteException findById(Long id);
}