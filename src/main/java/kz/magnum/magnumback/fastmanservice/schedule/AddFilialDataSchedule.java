package kz.magnum.magnumback.fastmanservice.schedule;

import kz.magnum.magnumback.fastmanservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddFilialDataSchedule {
    private final FilialService filialService;

    @Scheduled(cron = "0 0 5 * * ?")
    public void saveFilialData() {
        try {
            filialService.saveAll();
            log.info("Gold data was saved to Filial table ");
        } catch (Exception e) {
            log.error("Failed to save data to Filial table");
        }
    }
}
