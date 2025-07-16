package kz.magnum.magnumback.fastmanservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private long recordCount;
    private long pageCount;
    private int pageSize;
    private int pageNumber;
    private List<T> records;
}
