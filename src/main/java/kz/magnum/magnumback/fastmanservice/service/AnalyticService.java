package kz.magnum.magnumback.fastmanservice.service;

import java.util.Date;
import java.util.List;

public interface AnalyticService {
    List<?> getTasks(Date startDate,
                     Date endDate,
                     String listBy,
                     List<Short> siteCodes,
                     List<Short> statusCode,
                     List<Short> actionCode,
                     List<Long> incidentCode,
                     List<Date> date);
}
