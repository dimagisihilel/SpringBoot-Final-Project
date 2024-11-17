package lk.ijse.greenshadowprojectbackend.dao;


import lk.ijse.greenshadowprojectbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,String> {

}
