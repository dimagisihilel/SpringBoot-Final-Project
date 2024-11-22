package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.CropDao;
import lk.ijse.greenshadowprojectbackend.dao.FieldDao;
import lk.ijse.greenshadowprojectbackend.dao.LogDao;
import lk.ijse.greenshadowprojectbackend.dao.StaffDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.LogDto;
import lk.ijse.greenshadowprojectbackend.entity.CropEntity;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.entity.LogEntity;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.service.LogService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private CropDao cropDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping logMapping;

    @Override
    public LogDto save(LogDto dto) {
        dto.setLogId(AppUtil.generateLogId());
        LogEntity logEntity = logMapping.toLogEntity(dto);
        // Retrieve and set associated staff entities
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            Set<StaffEntity> staffEntities = new HashSet<>(staffDao.findAllById(dto.getStaffIds()));
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            logEntity.setStaffLogs(staffEntities);
        }
        // Retrieve and set associated field entities
        if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
            Set<FieldEntity> fieldEntities = new HashSet<>(fieldDao.findAllById(dto.getFieldIds()));
            if (fieldEntities.size() != dto.getFieldIds().size()) {
                throw new IllegalArgumentException("One or more field IDs are invalid.");
            }
            logEntity.setFieldLogs(fieldEntities);
        }
        // Retrieve and set associated crop entities
        if (dto.getCropIds() != null && !dto.getCropIds().isEmpty()) {
            Set<CropEntity> cropEntities = new HashSet<>(cropDao.findAllById(dto.getCropIds()));
            if (cropEntities.size() != dto.getCropIds().size()) {
                throw new IllegalArgumentException("One or more crop IDs are invalid.");
            }
            logEntity.setCropLogs(cropEntities);
        }
        // Save the log entity and map back to DTO
        LogEntity savedLog = logDao.save(logEntity);
        return logMapping.toLogDto(savedLog);
    }
    @Override
    public LogDto update(String id, LogDto dto) {
        LogEntity existingLog = logDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Log not found with ID: " + id));
        // Update basic fields
        existingLog.setLogDetails(dto.getLogDetails());
        existingLog.setDate( dto.getDate());
        // Handle image update if provided
        if (dto.getImage2() != null) {
            existingLog.setImage2(dto.getImage2());
        }
        // Update associated staff
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            List<StaffEntity> staffEntities = staffDao.findAllById(dto.getStaffIds());
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            existingLog.setStaffLogs(new HashSet<>(staffEntities));
        }
        // Update associated fields
        if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
            List<FieldEntity> fieldEntities = fieldDao.findAllById(dto.getFieldIds());
            if (fieldEntities.size() != dto.getFieldIds().size()) {
                throw new IllegalArgumentException("One or more field IDs are invalid.");
            }
            existingLog.setFieldLogs(new HashSet<>(fieldEntities));
        }
        // Update associated crops
        if (dto.getCropIds() != null && !dto.getCropIds().isEmpty()) {
            List<CropEntity> cropEntities = cropDao.findAllById(dto.getCropIds());
            if (cropEntities.size() != dto.getCropIds().size()) {
                throw new IllegalArgumentException("One or more crop IDs are invalid.");
            }
            existingLog.setCropLogs(new HashSet<>(cropEntities));
        }
        // Save the updated log entity and return the updated DTO
        return logMapping.toLogDto(logDao.save(existingLog));
    }
    @Override
    public void delete(String id) {
        logDao.deleteById(id);
    }
    @Override
    public LogDto findById(String id) {
        Optional<LogEntity> byId = logDao.findById(id);
        if (byId.isPresent()){
            return logMapping.toLogDto(byId.get());
        }
        return null;
    }
    @Override
    public List<LogDto> findAll() {
        return  logMapping.asLogDtoList(logDao.findAll());
    }
}