package kz.magnum.magnumback.fastmanservice.entity.fastman.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.magnum.magnumback.fastmanservice.entity.fastman.Status;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Action;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fastman_tasks", schema = "fastman")
public class FastmanTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fastman_task_id", nullable = false)
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "price")
    private Double price;
    @Column(name = "stock_qty")
    private Double stockQty;
    @Column(name = "date_action")
    private LocalDateTime dateAction;
    @Column(name = "executor")
    private String executor;
    @Column(name = "description")
    private String description;
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    @Column(name = "change_date")
    private LocalDateTime changeDate;
    @Column(name = "user_change")
    private String userChange;
    @Column(name = "date_completion")
    private LocalDateTime dateCompletion;
    @Column(name = "check_code")
    private String checkCode;
    @Column(name = "check_price")
    private Double checkPrice;
    @Column(name = "photo_completion")
    private String photoCompletion;
    @Column(name = "photo_create")
    private String photoCreate;
    @Column(name = "photo_original")
    private String photoOriginal;
    @Column(name = "file_task")
    private String fileTask;
    @Column(name = "date_check")
    private LocalDateTime dateCheck;
    @Column(name = "task_check")
    private Byte taskCheck;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "site_code")
    private Short siteCode;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "is_manual")
    private boolean isManual;
    @Column(name = "check_number")
    private String checkNumber;
    @Column(name = "check_date")
    private LocalDateTime checkDate;
    @Column(name = "check_price_manual")
    private Double checkPriceManual;
    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
    @JsonIgnore
    @OneToMany(mappedBy = "fastmanTask")
    private List<ExecutionTask> executionTasks;
    @Column(name = "department_code")
    private String departmentCode;

    @PrePersist
    void onCreate() {
        this.setDate(new Date());
    }

    @PreUpdate
    void onUpdate() {
        this.setChangeDate(LocalDateTime.now());
    }
}