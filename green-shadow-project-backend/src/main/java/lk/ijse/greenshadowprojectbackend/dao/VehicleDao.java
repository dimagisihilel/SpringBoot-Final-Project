package lk.ijse.greenshadowprojectbackend.dao;


import lk.ijse.greenshadowprojectbackend.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {

}
