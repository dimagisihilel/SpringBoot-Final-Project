package lk.ijse.greenshadowprojectbackend.dao;

import lk.ijse.greenshadowprojectbackend.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<CropEntity,String> {
}
