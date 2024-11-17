package lk.ijse.greenshadowprojectbackend.dao;

import lk.ijse.greenshadowprojectbackend.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<LogEntity,String> {
}
