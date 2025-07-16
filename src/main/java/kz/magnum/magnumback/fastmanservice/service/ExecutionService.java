package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Execution;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.TypeOfExecution;

import java.util.List;

public interface ExecutionService {
    List<Execution> findAll();

    List<TypeOfExecution> findAllTypes();

    Execution save(Execution execution);

    void delete(Long id);

    Execution update(Long id, Execution execution);

    Execution findById(Long id);
}