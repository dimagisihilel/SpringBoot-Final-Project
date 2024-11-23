package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.FieldDao;
import lk.ijse.greenshadowprojectbackend.dao.StaffDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowprojectbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.service.StaffService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping staffMapper;

    @Override
    public StaffDto save(StaffDto dto) {
        dto.setStaffId(AppUtil.generateStaffId());
        try {
            StaffEntity staffEntity = staffMapper.toStaffEntity(dto);

            if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
                // Retrieve and associate fields
                Set<FieldEntity> associatedFields = new HashSet<>();
                for (String fieldId : dto.getFieldIds()) {
                    FieldEntity field = fieldDao.findById(fieldId)
                            .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));
                    associatedFields.add(field);
                }
                staffEntity.setFields(new ArrayList<>(associatedFields));
            }

            // Save the staff entity
            StaffEntity savedStaff = staffDao.save(staffEntity);

            return staffMapper.toStaffDto(savedStaff);
        } catch (Exception e) {
            throw new RuntimeException("Error saving staff: " + e.getMessage(), e);
        }
    }

    @Override
    public StaffDto update(String id, StaffDto dto) {
        StaffEntity existingStaff = staffDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + id));

        // Update fields
        existingStaff.setFirstName(dto.getFirstName());
        existingStaff.setLastName(dto.getLastName());
        existingStaff.setLastName(dto.getGender());
        existingStaff.setLastName(dto.getDesignation());
        existingStaff.setEmail(dto.getEmail());
        existingStaff.setDob(dto.getDob());
        existingStaff.setAddress(dto.getAddress());
        existingStaff.setContact(dto.getContact());
        existingStaff.setJoinDate(dto.getJoinDate());
        existingStaff.setRole(dto.getRole());

        if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
            // Clear the current fields association to avoid duplicate entries
            existingStaff.getFields().clear();

            // Retrieve and associate fields
            for (String fieldId : dto.getFieldIds()) {
                FieldEntity field = fieldDao.findById(fieldId)
                        .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));

                // Add the staff to the field's staff list (bidirectional association)
                if (!field.getStaffMembers().contains(existingStaff)) {
                    field.getStaffMembers().add(existingStaff);
                }

                // Add the field to the staff's fields list
                existingStaff.getFields().add(field);
            }
        } else {
            // If no fields are provided, clear the association
            existingStaff.getFields().clear();
        }
        // Save the updated entity
        StaffEntity updatedEntity = staffDao.save(existingStaff);

        // Convert the updated entity back to DTO
        return staffMapper.toStaffDto(updatedEntity);
    }

    @Override
    public void delete(String id) {
        staffDao.deleteById(id);
    }

    @Override
    public StaffDto findById(String id) {
        Optional<StaffEntity> byId = staffDao.findById(id);
        if (byId.isPresent()){
            return staffMapper.toStaffDto(byId.get());
        }
        return null;
    }

    @Override
    public List<StaffDto> findAll() {
        return staffMapper.asStaffDtoList(staffDao.findAll());
    }

    @Override
    public Optional<StaffDto> findByEmail(String email) {
        Optional<StaffEntity> byEmail = staffDao.findByEmail(email);

        return byEmail.map(staffMapper::toStaffDto);

    }

    @Override
    public List<FieldDto> getFieldsOfStaffId(String staffId) {
        StaffEntity staff = staffDao.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + staffId));

        return staffMapper.asFieldDtoList(new ArrayList<>(staff.getFields()));
    }
}