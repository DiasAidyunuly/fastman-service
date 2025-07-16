package kz.magnum.magnumback.fastmanservice.entity.general;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "dir_fil", schema = "public")
public class FilialDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_fil_id", nullable = false)
    private Short id;
    @Column(name = "dir_fil_name")
    private String filialDirectorName;
    @Column(name = "dir_fil_tab")
    private String filialDirectorTab;
    @Column(name = "open")
    private Byte isOpen;
    @Column(name = "date_open")
    private LocalDateTime dateOpen;
    @Column(name = "date_close")
    private LocalDateTime dateClose;
    @ManyToOne
    @JoinColumn(name = "ter_dir_id")
    private TerritorialDirector territorialDirector;
    @ManyToOne
    @JoinColumn(name = "code_fil")
    private Filial filial;
}
