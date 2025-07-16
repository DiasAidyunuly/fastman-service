package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.general.Filial;
import kz.magnum.magnumback.fastmanservice.repository.fastman.FilialRepository;
import kz.magnum.magnumback.fastmanservice.repository.gold.CliadresRepository;
import kz.magnum.magnumback.fastmanservice.repository.gold.SiteRepository;
import kz.magnum.magnumback.fastmanservice.repository.gold.TraParpostesRepository;
import kz.magnum.magnumback.fastmanservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilialServiceImpl implements FilialService {
    private final FilialRepository filialRepository;
    private final SiteRepository siteRepository;
    private final TraParpostesRepository traParpostesRepository;
    private final CliadresRepository cliadresRepository;

    @Override
    public void saveAll() {
        List<Filial> filials = new ArrayList<>();
        List<String> codeFils = siteRepository.findAllCodeFils();
        for (String codeFil : codeFils) {
            Integer filialCode = Integer.valueOf(codeFil);
            Integer codeSiteTz = Integer.valueOf(siteRepository.findCodeSiteTz(filialCode));
            String nameSiteTz = siteRepository.findNameSiteTz(codeSiteTz);
            Integer codeSiteSp = Integer.valueOf(siteRepository.findCodeSiteSp(filialCode));
            String nameSiteSp = siteRepository.findNameSiteSp(codeSiteSp);
            String format = traParpostesRepository.findFormat(filialCode);
            String nameFil = cliadresRepository.findNameFil(codeSiteTz);
            String city = cliadresRepository.findCity(codeSiteTz);
            String addressFil = cliadresRepository.findAddressFil(codeSiteTz);
            Filial filial = Filial.builder()
                .codeFilial(filialCode)
                .codeSiteTz(codeSiteTz)
                .nameSiteTz(nameSiteTz)
                .codeSiteSp(codeSiteSp)
                .nameSiteSp(nameSiteSp)
                .format(format)
                .nameFil(nameFil)
                .city(city)
                .addressFil(addressFil)
                .build();
            filials.add(filial);
        }
        filialRepository.saveAll(filials);
    }
}
