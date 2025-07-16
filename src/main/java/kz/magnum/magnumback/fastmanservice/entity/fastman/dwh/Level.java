package kz.magnum.magnumback.fastmanservice.entity.fastman.dwh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "levels", schema = "fastman")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id", nullable = false)
    private Long id;
    @Column(name = "level_name")
    private String levelName;
    @Column(name = "level_short_name")
    private String levelShortName;

}