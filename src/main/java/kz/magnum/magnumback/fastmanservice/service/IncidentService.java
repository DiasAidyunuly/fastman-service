package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;

import java.util.List;

public interface IncidentService {
    List<Incident> findAll();

    Incident save(Incident incident);

    void delete(Long id);

    Incident update(Long id, Incident incident);

    Incident findById(Long id);
}
