package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowprojectbackend.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Override
    public StaffDto save(StaffDto dto) {
        return null;
    }
    @Override
    public StaffDto update(String id, StaffDto dto) {
        return null;
    }
    @Override
    public void delete(String id) {
    }
    @Override
    public StaffDto findById(String id) {
        return null;
    }
    @Override
    public List<StaffDto> findAll() {
        return null;
    }
}