package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class StructureId implements Serializable {
    private Long strcint;
    private Long strpere;
    private Short strprof;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StructureId that = (StructureId) object;
        return Objects.equals(strcint, that.strcint) && Objects.equals(strpere, that.strpere) && Objects.equals(strprof, that.strprof);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strcint, strpere, strprof);
    }
}