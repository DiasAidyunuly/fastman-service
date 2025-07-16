package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.Status;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.FastmanTaskRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.StatusRepository;
import kz.magnum.magnumback.fastmanservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private final FastmanTaskRepository fastmanTaskRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Short id) {
        fastmanTaskRepository.setStatusToNull(id);
        statusRepository.deleteById(id);
    }

    @Override
    public Status findById(Short id) {
        return statusRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Статус с таким ID = %d не найден", id)));
    }
}