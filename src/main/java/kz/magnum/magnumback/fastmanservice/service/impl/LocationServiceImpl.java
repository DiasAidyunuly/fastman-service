package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.dwh.Location;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.LocationRepository;
import kz.magnum.magnumback.fastmanservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location findById(Short id) {
        return locationRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Сайт с таким ID %d не найден", id)));
    }
}
