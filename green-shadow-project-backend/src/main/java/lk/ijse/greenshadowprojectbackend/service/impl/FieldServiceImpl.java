package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dto.impl.FieldDto;
import lk.ijse.greenshadowprojectbackend.service.FieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Override
    public FieldDto save(FieldDto dto) {
        return null;
    }
    @Override
    public FieldDto update(String id, FieldDto dto) {
        return null;
    }
    @Override
    public void delete(String id) {
    }
    @Override
    public FieldDto findById(String id) {
        return null;
    }
    @Override
    public List<FieldDto> findAll() {
        return null;
    }
}
