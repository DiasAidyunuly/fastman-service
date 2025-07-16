package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class StrucrelId implements Serializable {
    private Long objcint;
    private Long objpere;
    private Date objddeb;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StrucrelId that = (StrucrelId) object;
        return Objects.equals(objcint, that.objcint) && Objects.equals(objpere, that.objpere) && Objects.equals(objddeb, that.objddeb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objcint, objpere, objddeb);
    }
}