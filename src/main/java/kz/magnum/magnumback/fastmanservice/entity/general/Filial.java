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
@Table(name = "filials", schema = "public")
public class Filial {
    @Id
    @Column(name = "code_fil", nullable = false)
    private Integer codeFilial;
    @Column(name = "code_site_tz")
    private Integer codeSiteTz;
    @Column(name = "name_site_tz")
    private String nameSiteTz;
    @Column(name = "code_site_sp")
    private Integer codeSiteSp;
    @Column(name = "name_site_sp")
    private String nameSiteSp;
    @Column(name = "format")
    private String format;
    @Column(name = "name_fil")
    private String nameFil;
    @Column(name = "city")
    private String city;
    @Column(name = "address_fil")
    private String addressFil;
    @OneToMany(mappedBy = "filial")
    private List<FilialDirector> filialDirectors;
}
