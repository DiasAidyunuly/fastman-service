package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll();

    void delete(Short id);

    Status findById(Short id);
}