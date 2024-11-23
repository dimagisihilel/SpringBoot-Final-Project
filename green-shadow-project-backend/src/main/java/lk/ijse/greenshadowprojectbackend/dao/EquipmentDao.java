package lk.ijse.greenshadowprojectbackend.dao;


import lk.ijse.greenshadowprojectbackend.entity.EquipmentEntity;
import lk.ijse.greenshadowprojectbackend.entity.FieldEntity;
import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
    List<EquipmentEntity> findByStaff(StaffEntity staff);
    List<EquipmentEntity> findByField(FieldEntity field);

}
