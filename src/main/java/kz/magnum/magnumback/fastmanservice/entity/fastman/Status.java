package kz.magnum.magnumback.fastmanservice.entity.fastman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statuses", schema = "fastman")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Short id;
    @Column(name = "status_code")
    private Integer statusCode;
    @Column(name = "status_name")
    private String statusName;
    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private List<FastmanTask> fastmanTasks;
}