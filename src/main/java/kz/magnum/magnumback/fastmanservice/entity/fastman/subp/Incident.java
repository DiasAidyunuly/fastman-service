package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

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
@Table(name = "incidents", schema = "fastman")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id", nullable = false)
    private Long id;
    @Column(name = "incident_name")
    private String incidentName;
    @Column(name = "complete_day")
    private Integer completeDay;
    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
    @Column(name = "quarantine")
    private Byte quarantine;
    @Column(name = "qty_task")
    private Integer qty_task;
    @Column(name = "disable")
    private Byte disable;
    @Column(name = "mon")
    private Byte mon;
    @Column(name = "tue")
    private Byte tue;
    @Column(name = "wed")
    private Byte wed;
    @Column(name = "thu")
    private Byte thu;
    @Column(name = "fri")
    private Byte fri;
    @Column(name = "sat")
    private Byte sat;
    @Column(name = "sun")
    private Byte sun;
    @Column(name = "priority")
    private Short priority;
    @JsonIgnore
    @OneToMany(mappedBy = "incident")
    private List<IncExecution> incExecutions;
    @JsonIgnore
    @OneToMany(mappedBy = "incident")
    private List<IncAction> incActions;
    @JsonIgnore
    @OneToMany(mappedBy = "incident")
    private List<SiteException> siteExceptions;
    @JsonIgnore
    @OneToMany(mappedBy = "incident")
    private List<FastmanTask> fastmanTasks;
}