package kz.magnum.magnumback.fastmanservice.consumer;

import kz.magnum.magnumback.fastmanservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FastmanKafkaConsumer {
    private final TaskService taskService;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "test-consumer-group")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        log.info("Received message: {}", record.value());
        taskService.saveTaskFromKafka(record.value());
    }
}