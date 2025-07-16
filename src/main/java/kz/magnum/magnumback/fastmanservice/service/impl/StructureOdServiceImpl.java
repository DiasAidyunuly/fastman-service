package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.general.*;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.mapper.StructureOdMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.DivisionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.FilialDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.RegionalDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.TerritorialDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.*;
import kz.magnum.magnumback.fastmanservice.repository.fastman.*;
import kz.magnum.magnumback.fastmanservice.service.StructureOdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructureOdServiceImpl implements StructureOdService {
    private final StructureOdMapper structureOdMapper;
    private final DivisionRepository divisionRepository;
    private final RegionalDirectorRepository regionalDirectorRepository;
    private final TerritorialDirectorRepository territorialDirectorRepository;
    private final FilialDirectorRepository filialDirectorRepository;
    private final FilialRepository filialRepository;
    private String RD = "RD";
    private String TD = "TD";

    @Override
    public List<DivisionModel> getStructureOd(StructureOdFilterParam filter) {
        List<DivisionModel> divisionModels = divisionRepository.findAll()
            .stream()
            .map(structureOdMapper::toDivisionDto)
            .collect(Collectors.toList());
        return filterDivisions(divisionModels, filter);
    }

    @Override
    public Division saveDivision(Division division) {
        return divisionRepository.save(division);
    }

    @Override
    @Transactional
    public void deleteDivision(Short id) {
        regionalDirectorRepository.setDivisionToNull(id);
        divisionRepository.deleteById(id);
    }

    @Override
    public Division updateDivision(Short id, DivisionDto dto) {
        Division division = divisionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Дивизион с таким ID = %d не найден", id)));
        division.setDivisionName(dto.getDivisionName());
        return divisionRepository.save(division);
    }

    @Override
    public RegionalDirector saveRegionalDirector(RegionalDirectorDto dto) {
        Division division = divisionRepository.findById(dto.getDivisionId()).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Дивизион с таким ID = %d не найден", dto.getDivisionId())));
        return regionalDirectorRepository.save(RegionalDirector.builder()
            .regionalDirectorName(dto.getRegionalDirectorName())
            .regionalDirectorTab(dto.getRegionalDirectorTab())
            .division(division)
            .build());
    }

    @Override
    @Transactional
    public void deleteRegionalDirector(Short id) {
        territorialDirectorRepository.setRegionalDirectorToNull(id);
        regionalDirectorRepository.deleteById(id);
    }

    @Override
    public RegionalDirector updateRegionalDirector(Short id, RegionalDirectorDto dto) {
        RegionalDirector regionalDirector = regionalDirectorRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Региональный директор с таким ID = %d не найден", id)));
        regionalDirector.setRegionalDirectorName(dto.getRegionalDirectorName());
        regionalDirector.setRegionalDirectorTab(dto.getRegionalDirectorTab());
        return regionalDirectorRepository.save(regionalDirector);
    }

    @Override
    public TerritorialDirector saveTerritorialDirector(TerritorialDirectorDto dto) {
        RegionalDirector regionalDirector = regionalDirectorRepository.findById(dto.getRegionalDirectorId()).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Региональный директор с таким ID = %d не найден", dto.getRegionalDirectorId())));
        return territorialDirectorRepository.save(TerritorialDirector.builder()
            .territorialDirectorName(dto.getTerritorialDirectorName())
            .territorialDirectorTab(dto.getTerritorialDirectorTab())
            .region(dto.getRegion())
            .regionalDirector(regionalDirector)
            .build());
    }

    @Override
    @Transactional
    public void deleteTerritorialDirector(Short id) {
        filialDirectorRepository.setTerritorialDirectorToNull(id);
        territorialDirectorRepository.deleteById(id);
    }

    @Override
    public TerritorialDirector updateTerritorialDirector(Short id, TerritorialDirectorDto dto) {
        TerritorialDirector territorialDirector = territorialDirectorRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Территориальный директор с таким ID = %d не найден", id)));
        territorialDirector.setTerritorialDirectorName(dto.getTerritorialDirectorName());
        territorialDirector.setTerritorialDirectorTab(dto.getTerritorialDirectorTab());
        territorialDirector.setRegion(dto.getRegion());
        return territorialDirectorRepository.save(territorialDirector);
    }

    @Override
    public List<Filial> getFilials() {
        return filialRepository.findAll();
    }

    @Override
    public FilialDirector saveFilialDirector(FilialDirectorDto dto) {
        TerritorialDirector territorialDirector = territorialDirectorRepository.findById(dto.getTerritorialDirectorId()).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Территориальный директор с таким ID = %d не найден", dto.getTerritorialDirectorId())));
        Filial filial = filialRepository.findById(dto.getCodeFilial()).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Филиал с таким ID = %d не найден", dto.getCodeFilial())));
        return filialDirectorRepository.save(FilialDirector.builder()
            .filialDirectorName(dto.getFilialDirectorName())
            .filialDirectorTab(dto.getFilialDirectorTab())
            .isOpen(dto.getIsOpen())
            .dateOpen(dto.getDateOpen() == null ? null : dto.getDateOpen().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
            .dateClose(dto.getDateClose() == null ? null : dto.getDateClose().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
            .territorialDirector(territorialDirector)
            .filial(filial)
            .build());
    }

    @Override
    public void deleteFilialDirector(Short id) {
        filialDirectorRepository.deleteById(id);
    }

    @Override
    public FilialDirector updateFilialDirector(Short id, FilialDirectorDto dto) {
        FilialDirector filialDirector = filialDirectorRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Директор филиала с таким ID = %d не найден", id)));
        filialDirector.setFilialDirectorName(dto.getFilialDirectorName());
        filialDirector.setFilialDirectorTab(dto.getFilialDirectorTab());
        filialDirector.setIsOpen(dto.getIsOpen());
        filialDirector.setDateOpen(dto.getDateOpen() == null ? null : dto.getDateOpen().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        filialDirector.setDateClose(dto.getDateClose() == null ? null : dto.getDateClose().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        if (dto.getCodeFilial() != null) {
            Filial filial = filialRepository.findById(dto.getCodeFilial()).orElseThrow(
                () -> new FastmanNotFoundException(String.format("Директор филиала с таким ID = %d не найден", id)));
            filialDirector.setFilial(filial);
        }
        return filialDirectorRepository.save(filialDirector);
    }

    @Override
    public List<Filial> getFilialsByTabNumber(String tabNumber) {
        List<RegionalDirector> regionalDirectors = regionalDirectorRepository.findByTabNumber(tabNumber);
        if (regionalDirectors != null && !regionalDirectors.isEmpty()) {
            return regionalDirectors.stream()
                .flatMap(rd -> rd.getTerritorialDirectors().stream())
                .flatMap(td -> td.getFilialDirectors().stream())
                .map(FilialDirector::getFilial)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        }
        List<TerritorialDirector> territorialDirectors = territorialDirectorRepository.findByTabNumber(tabNumber);
        if (territorialDirectors != null && !territorialDirectors.isEmpty()) {
            return territorialDirectors.stream()
                .flatMap(td -> td.getFilialDirectors().stream())
                .map(FilialDirector::getFilial)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        }
        return null;
    }

    public List<DivisionModel> filterDivisions(List<DivisionModel> divisions, StructureOdFilterParam filter) {
        return divisions.stream()
            .map(division -> filterDivision(division, filter))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private DivisionModel filterDivision(DivisionModel division, StructureOdFilterParam filter) {
        List<RegionalDirectorModel> filteredRegionals = division.getRegionalDirectorModels().stream()
            .map(regional -> {
                if (filter.getRegionalDirectors() != null &&
                    !filter.getRegionalDirectors().contains(regional.getRegionalDirectorTab())) {
                    return null;
                }

                List<TerritorialDirectorModel> filteredTerritorials = regional.getTerritorialDirectorModels().stream()
                    .map(territorial -> {
                        if (filter.getTerritorialDirectors() != null &&
                            !filter.getTerritorialDirectors().contains(territorial.getTerritorialDirectorTab())) {
                            return null;
                        }

                        List<FilialDirectorModel> filteredFilials = territorial.getFilialDirectorModels().stream()
                            .filter(fd -> {
                                if (filter.getFilialDirectors() != null &&
                                    !filter.getFilialDirectors().contains(fd.getFilialDirectorTab())) return false;

                                if (filter.getIsOpen() != null &&
                                    !filter.getIsOpen().equals(fd.getIsOpen())) return false;

                                FilialModel filial = fd.getFilialModel();
                                if (filial == null) return false;

                                if (filter.getFilials() != null &&
                                    !filter.getFilials().contains(filial.getCodeFilial().shortValue())) return false;

                                if (filter.getFormats() != null &&
                                    !filter.getFormats().contains(filial.getFormat())) return false;

                                return filter.getCities() == null ||
                                    filter.getCities().contains(filial.getCity());
                            })
                            .collect(Collectors.toList());

                        if (filteredFilials.isEmpty()) return null;

                        return TerritorialDirectorModel.builder()
                            .territorialDirectorId(territorial.getTerritorialDirectorId())
                            .territorialDirectorName(territorial.getTerritorialDirectorName())
                            .territorialDirectorTab(territorial.getTerritorialDirectorTab())
                            .region(territorial.getRegion())
                            .parentId(territorial.getParentId())
                            .filialDirectorModels(filteredFilials)
                            .build();
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

                if (filteredTerritorials.isEmpty()) return null;

                return RegionalDirectorModel.builder()
                    .regionalDirectorId(regional.getRegionalDirectorId())
                    .regionalDirectorName(regional.getRegionalDirectorName())
                    .regionalDirectorTab(regional.getRegionalDirectorTab())
                    .parentId(regional.getParentId())
                    .territorialDirectorModels(filteredTerritorials)
                    .build();
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (filteredRegionals.isEmpty()) return null;

        return DivisionModel.builder()
            .divisionId(division.getDivisionId())
            .divisionName(division.getDivisionName())
            .regionalDirectorModels(filteredRegionals)
            .build();
    }
}