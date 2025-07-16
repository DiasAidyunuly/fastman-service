package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.exception.IntegrationGoldClientNotFoundException;
import kz.magnum.magnumback.fastmanservice.mapper.IncidentNameMapper;
import kz.magnum.magnumback.fastmanservice.model.IncidentName;
import kz.magnum.magnumback.fastmanservice.model.NewSiteException;
import kz.magnum.magnumback.fastmanservice.model.SiteWithCodeAndName;
import kz.magnum.magnumback.fastmanservice.repository.fastman.FilialRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncidentRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.SiteExceptionRepository;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import kz.magnum.magnumback.fastmanservice.service.SiteExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteExceptionServiceImpl implements SiteExceptionService {
    private final SiteExceptionRepository siteExceptionRepository;
    private final IncidentService incidentService;
    private final IncidentRepository incidentRepository;
    private final IncidentNameMapper incidentNameMapper;
    private final FilialRepository filialRepository;

    @Override
    public List<NewSiteException> findAllNewSites() {
        try {
            List<SiteWithCodeAndName> sites = new ArrayList<>();
            List<SiteWithCodeAndName> sitesSp = filialRepository.findCodeSiteSpAndNameSiteSp();
            List<SiteWithCodeAndName> sitesTz = filialRepository.findCodeSiteTzAndNameSiteTz();
            sites.addAll(sitesSp);
            sites.addAll(sitesTz);
            List<IncidentName> incidentNames = fetchIncidentNames();
            NewSiteException newSiteException = NewSiteException.builder()
                .incidents(incidentNames)
                .sites(sites)
                .build();
            return Collections.singletonList(newSiteException);
        } catch (Exception e) {
            log.error("Error while fetching new sites", e);
            throw new IntegrationGoldClientNotFoundException("Failed to fetch new sites", e);
        }
    }

    @Override
    public List<SiteException> findAll() {
        return siteExceptionRepository.findAll();
    }

    @Override
    public SiteException save(SiteException siteExceptionParam) {
        siteExceptionParam.setIncident(incidentService.findById(siteExceptionParam.getIncident().getId()));
        return siteExceptionRepository.save(siteExceptionParam);
    }

    @Override
    public void delete(Long id) {
        siteExceptionRepository.deleteById(id);
    }

    @Override
    public SiteException update(Long id, Integer excQtyTask) {
        SiteException siteExceptionResult = findById(id);
        siteExceptionResult.setExcQtyTask(excQtyTask);
        return siteExceptionRepository.save(siteExceptionResult);
    }

    @Override
    public SiteException findById(Long id) {
        return siteExceptionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Исключения по сайтам с таким ID %d не найдено", id)));
    }

    private List<IncidentName> fetchIncidentNames() {
        return incidentRepository.findAll().stream()
            .map(incidentNameMapper::toDomain)
            .collect(Collectors.toList());
    }
}