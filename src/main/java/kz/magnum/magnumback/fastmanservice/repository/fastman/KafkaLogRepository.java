package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.KafkaLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaLogRepository extends JpaRepository<KafkaLog, Long> {
}
