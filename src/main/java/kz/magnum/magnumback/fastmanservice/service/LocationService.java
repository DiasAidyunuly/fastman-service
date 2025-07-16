package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.dwh.Location;

public interface LocationService {
    Location findById(Short id);
}
