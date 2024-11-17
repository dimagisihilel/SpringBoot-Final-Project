package lk.ijse.greenshadowprojectbackend.dao;

import lk.ijse.greenshadowprojectbackend.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffDao extends JpaRepository<StaffEntity,String> {

}
