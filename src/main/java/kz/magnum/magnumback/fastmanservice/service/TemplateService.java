package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Template;

import java.util.List;

public interface TemplateService {

    List<Template> findAll();

    Template save(Template template);

    void delete(Short id);

    Template update(Short id, Template template);

    Template findById(Short id);
}
