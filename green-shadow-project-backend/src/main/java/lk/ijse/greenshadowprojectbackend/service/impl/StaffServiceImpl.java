package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.StaffDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.service.StaffService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping staffMapper;

    @Override
    public StaffDto save(StaffDto dto) {
        dto.setStaffId(AppUtil.generateStaffId());
        StaffEntity save = staffDao.save(staffMapper.toStaffEntity(dto));
        if(save==null){
            System.out.println("not saved staff data");
            //throw new DataPersistException(" Staff not saved");
        }
        return staffMapper.toStaffDto(save);
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
    @Override
    public Optional<StaffDto> findByEmail(String email) {
        Optional<StaffEntity> byEmail = staffDao.findByEmail(email);
        return byEmail.map(staffMapper::toStaffDto);
    }
}