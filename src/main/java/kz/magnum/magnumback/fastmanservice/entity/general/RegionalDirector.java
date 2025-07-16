package kz.magnum.magnumback.fastmanservice.entity.general;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reg_dir", schema = "public")
public class RegionalDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_dir_id", nullable = false)
    private Short id;
    @Column(name = "reg_dir_name")
    private String regionalDirectorName;
    @Column(name = "reg_dir_tab")
    private String regionalDirectorTab;
    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
    @OneToMany(mappedBy = "regionalDirector", fetch = FetchType.EAGER)
    private List<TerritorialDirector> territorialDirectors;
}
