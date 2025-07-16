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
@Table(name = "item_properties", schema = "fastman")
public class ItemProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_property_id", nullable = false)
    private Long id;
    @Column(name = "apolloaddr")
    private String apolloAddr;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
