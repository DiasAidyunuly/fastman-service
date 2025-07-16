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
@Table(name = "ter_dir", schema = "public")
public class TerritorialDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ter_dir_id", nullable = false)
    private Short id;
    @Column(name = "ter_dir_name")
    private String territorialDirectorName;
    @Column(name = "ter_dir_tab")
    private String territorialDirectorTab;
    @Column(name = "region")
    private String region;
    @ManyToOne
    @JoinColumn(name = "reg_dir_id")
    private RegionalDirector regionalDirector;
    @OneToMany(mappedBy = "territorialDirector", fetch = FetchType.EAGER)
    private List<FilialDirector> filialDirectors;
}
