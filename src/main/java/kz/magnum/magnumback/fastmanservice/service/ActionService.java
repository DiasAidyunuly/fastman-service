package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Action;

import java.util.List;

public interface ActionService {
    List<Action> findAll();

    Action save(Action action);

    void delete(Short id);

    Action update(Short id, Action action);

    Action findById(Short id);
}
