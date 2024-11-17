package lk.ijse.greenshadowprojectbackend.service;


import lk.ijse.greenshadowprojectbackend.dto.impl.EquipmentDto;

import java.util.List;

public interface EquipmentService extends BaseService<EquipmentDto> {
    List<EquipmentDto> getEquipmentByStaffId(String staffId);
    List<EquipmentDto> getEquipmentByFieldId(String fieldId);

}
