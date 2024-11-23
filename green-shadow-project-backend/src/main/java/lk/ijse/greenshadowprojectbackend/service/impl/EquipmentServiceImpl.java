package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.EquipmentDao;
import lk.ijse.greenshadowprojectbackend.dao.FieldDao;
import lk.ijse.greenshadowprojectbackend.dao.StaffDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.EquipmentDto;
import lk.ijse.greenshadowprojectbackend.entity.EquipmentEntity;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.service.EquipmentService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping equipmentMapper;
    @Override
    public EquipmentDto save(EquipmentDto dto) {
        dto.setEquipmentId(AppUtil.generateEquipmentId());
        EquipmentEntity equipment = equipmentMapper.toEquipmentEntity(dto);
        equipment = equipmentDao.save(equipment);
        return equipmentMapper.toEquipmentDto(equipment);
    }
    @Override
    public EquipmentDto update(String id, EquipmentDto dto) {
        EquipmentEntity equipment = equipmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + id));
        equipment.setType(dto.getType());
        equipment.setName(dto.getName());
        equipment.setStatus(dto.getStatus());
        if (dto.getFieldId() != null) {
            FieldEntity field = fieldDao.findById(dto.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + dto.getFieldId()));
            equipment.setField(field);
        }
        if (dto.getStaffId() != null) {
            StaffEntity staff = staffDao.findById(dto.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + dto.getStaffId()));
            equipment.setStaff(staff);
        }
        return equipmentMapper.toEquipmentDto(equipmentDao.save(equipment));
    }
    @Override
    public void delete(String id) {
        equipmentDao.deleteById(id);
    }
    @Override
    public EquipmentDto findById(String id) {
        EquipmentEntity equipment = equipmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + id));
        return equipmentMapper.toEquipmentDto(equipment);
    }
    @Override
    public List<EquipmentDto> findAll() {
        return equipmentMapper.asEquipmentDtoList(equipmentDao.findAll());
    }

    @Override
    public List<EquipmentDto> getEquipmentByStaffId(String staffId) {
        StaffEntity staff = staffDao.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + staffId));
        List<EquipmentEntity> equipmentList = equipmentDao.findByStaff(staff);
        return equipmentMapper.asEquipmentDtoList(equipmentList);
    }

    @Override
    public List<EquipmentDto> getEquipmentByFieldId(String fieldId) {
        FieldEntity field = fieldDao.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));
        List<EquipmentEntity> equipmentList = equipmentDao.findByField(field);
        return equipmentMapper.asEquipmentDtoList(equipmentList);
    }
}