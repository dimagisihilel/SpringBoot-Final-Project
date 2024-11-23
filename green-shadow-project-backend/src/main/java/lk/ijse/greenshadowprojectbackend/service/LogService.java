package lk.ijse.greenshadowprojectbackend.service;


import lk.ijse.greenshadowprojectbackend.dto.impl.LogDto;

import java.util.Map;

public interface LogService extends BaseService<LogDto> {
    Map<String, Object> getRelatedEntitiesAsDtos(String logId);
}
