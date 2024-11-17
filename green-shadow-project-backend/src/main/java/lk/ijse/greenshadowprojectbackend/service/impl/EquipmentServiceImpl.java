package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowprojectbackend.service.EquipmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Override
    public EquipmentDto save(EquipmentDto dto) {
        return null;
    }
    @Override
    public EquipmentDto update(String id, EquipmentDto dto) {
        return null;
    }
    @Override
    public void delete(String id) {
    }
    @Override
    public EquipmentDto findById(String id) {
        return null;
    }
    @Override
    public List<EquipmentDto> findAll() {
        return null;
    }
}