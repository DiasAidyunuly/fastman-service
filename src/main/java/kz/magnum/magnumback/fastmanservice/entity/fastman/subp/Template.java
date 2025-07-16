package kz.magnum.magnumback.fastmanservice.entity.fastman.subp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "templates", schema = "fastman")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id", nullable = false)
    private Short id;
    @Column(name = "template_name")
    private String templateName;
    @Column(name = "is_manual")
    private boolean manual;
    @JsonIgnore
    @OneToMany(mappedBy = "template")
    private List<Incident> incidents;
}