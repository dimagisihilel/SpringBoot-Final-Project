package lk.ijse.greenshadowprojectbackend.dao;


import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import lk.ijse.greenshadowprojectbackend.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
    List<VehicleEntity> findByStaff(StaffEntity staff);
}
