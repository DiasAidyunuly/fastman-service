package kz.magnum.magnumback.fastmanservice.entity.fastman;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kafka_logs", schema = "fastman")
public class KafkaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "status")
    private Byte status;
    @Column(name = "body")
    private String body;
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Column(name = "err_text")
    private String errText;
}
