package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Execution;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.TypeOfExecution;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.ExecutionRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncExecutionRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.TypeOfExecutionRepository;
import kz.magnum.magnumback.fastmanservice.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {
    private final ExecutionRepository executionRepository;
    private final IncExecutionRepository incExecutionRepository;
    private final TypeOfExecutionRepository typeOfExecutionRepository;

    @Override
    public List<Execution> findAll() {
        return executionRepository.findAll();
    }

    @Override
    public List<TypeOfExecution> findAllTypes() {
        return typeOfExecutionRepository.findAll();
    }

    @Override
    public Execution save(Execution executionParam) {
        return executionRepository.save(executionParam);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        incExecutionRepository.deleteByExecutionId(id);
        executionRepository.deleteById(id);
    }

    @Override
    public Execution update(Long id, Execution executionParam) {
        findById(id);
        executionParam.setId(id);
        return executionRepository.save(executionParam);
    }

    @Override
    public Execution findById(Long id) {
        return executionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Исполнение с таким ID = %d не найдено", id)));
    }
}