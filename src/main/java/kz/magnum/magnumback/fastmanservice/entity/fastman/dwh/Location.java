package kz.magnum.magnumback.fastmanservice.entity.fastman.dwh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations", schema = "fastman")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Short id;
    @Column(name = "site_code")
    private Short siteCode;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "format_name")
    private String formatName;
    @Column(name = "change_date")
    private LocalDateTime changeDate;
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Supplier> suppliers;
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<ItemProperty> itemProperties;
}
