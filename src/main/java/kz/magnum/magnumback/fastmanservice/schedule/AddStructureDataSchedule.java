package kz.magnum.magnumback.fastmanservice.schedule;

import kz.magnum.magnumback.fastmanservice.service.structure.StructureSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddStructureDataSchedule {
    private final StructureSchedulerService structureSchedulerService;

    @Scheduled(cron = "0 0 5 * * ?")
    public void saveStructureData() {
        structureSchedulerService.saveToStructureTable();
    }
}