package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.FieldDao;
import lk.ijse.greenshadowprojectbackend.dao.StaffDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowprojectbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.service.FieldService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping fieldMapping;
    @Override
    public FieldDto save(FieldDto dto) {
        dto.setFieldId(AppUtil.generateFieldId());
        FieldEntity field = fieldMapping.toFieldEntity(dto);
        // Associate staff with field
        Set<StaffEntity> staffEntities = new HashSet<>();
        if (dto.getStaffIds() != null) {
            for (String staffId : dto.getStaffIds()) {
                StaffEntity staff = staffDao.findById(staffId)
                        .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + staffId));
                staffEntities.add(staff);
            }
        }
        field.setStaffMembers(staffEntities);
        return fieldMapping.toFieldDto(fieldDao.save(field));
    }
    @Override
    public FieldDto update(String id, FieldDto dto) {
        FieldEntity existingField = fieldDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));
        // Update basic field properties
        existingField.setName(dto.getName());
        existingField.setSize(dto.getSize());
        existingField.setLocation(dto.getLocation());

        // Update staff members if provided
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            List<StaffEntity> staffEntities = staffDao.findAllById(dto.getStaffIds());
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            existingField.setStaffMembers(new HashSet<>(staffEntities));
        } else {
            existingField.getStaffMembers().clear();  // Clear existing staff if no IDs are provided
        }

        // Handle images if provided
        if (dto.getImage1() != null) {
            existingField.setImage1(dto.getImage1());
        }
        if (dto.getImage2() != null) {
            existingField.setImage2(dto.getImage2());
        }
        // Save the updated field entity
        return fieldMapping.toFieldDto(fieldDao.save(existingField));
    }
    @Override
    public void delete(String id) {
        fieldDao.deleteById(id);
    }
    @Override
    public FieldDto findById(String id) {
        Optional<FieldEntity> byId = fieldDao.findById(id);
        if (byId.isPresent()){
            return fieldMapping.toFieldDto(byId.get());
        }
        return null;
    }
    @Override
    public List<FieldDto> findAll() {
        return fieldMapping.asFieldDtoList(fieldDao.findAll());
    }

    @Override
    public List<StaffDto> getStaffIdsByFieldId(String fieldId) {
        FieldEntity field = fieldDao.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));

        return fieldMapping.asStaffDtoList(new ArrayList<>(field.getStaffMembers()));
    }
}
