package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.CropDao;
import lk.ijse.greenshadowprojectbackend.dao.FieldDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.CropDto;
import lk.ijse.greenshadowprojectbackend.entity.CropEntity;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.service.CropService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping cropMapping;
    @Autowired
    private FieldDao fieldDao;

    @Override
    public CropDto save(CropDto dto) {
        dto.setId(AppUtil.generateCropId());
        return cropMapping.toCropDto(cropDao.save(cropMapping.toCropEntity(dto)));
    }
    @Override
    public CropDto update(String id, CropDto dto) {
        CropEntity existingCrop = cropDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with ID: " + id));
        // Update basic crop properties
        existingCrop.setCommonName(dto.getCommonName());
        existingCrop.setSpecificName(dto.getSpecificName());
        existingCrop.setCategory(dto.getCategory());
        existingCrop.setSeason(dto.getSeason());
        // Set the field if provided in the DTO
        if (dto.getFieldId() != null) {
            FieldEntity field = fieldDao.findById(dto.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + dto.getFieldId()));
            existingCrop.setField(field);
        }
        // Handle images if provided
        if (dto.getImage1() != null) {
            existingCrop.setImage1(dto.getImage1());
        }
        // Save the updated crop entity
        return cropMapping.toCropDto(cropDao.save(existingCrop));
    }
    @Override
    public void delete(String id) {
        cropDao.deleteById(id);
    }
    @Override
    public CropDto findById(String id) {
        Optional<CropEntity> byId = cropDao.findById(id);
        if (byId.isPresent()){
            return cropMapping.toCropDto(byId.get());
        }
        return null;
    }
    @Override
    public List<CropDto> findAll() {
        return cropMapping.asCropDtoList(cropDao.findAll());
    }
}