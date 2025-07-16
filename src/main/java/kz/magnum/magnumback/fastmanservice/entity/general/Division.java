package kz.magnum.magnumback.fastmanservice.entity.general;

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
@Table(name = "division", schema = "public")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id", nullable = false)
    private Short id;
    @Column(name = "division_name")
    private String divisionName;
    @OneToMany(mappedBy = "division", fetch = FetchType.EAGER)
    private List<RegionalDirector> regionalDirectors;
}
