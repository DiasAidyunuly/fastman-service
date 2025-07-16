package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Template;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncidentRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.TemplateRepository;
import kz.magnum.magnumback.fastmanservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    private final IncidentRepository incidentRepository;

    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public Template save(Template templateParam) {
        return templateRepository.save(templateParam);
    }

    @Override
    @Transactional
    public void delete(Short id) {
        incidentRepository.setTemplateToNull(id);
        templateRepository.deleteById(id);
    }

    @Override
    public Template update(Short id, Template templateParam) {
        findById(id);
        templateParam.setId(id);
        return templateRepository.save(templateParam);
    }

    @Override
    public Template findById(Short id) {
        return templateRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Шаблон с таким ID = %d не найден", id)));
    }
}