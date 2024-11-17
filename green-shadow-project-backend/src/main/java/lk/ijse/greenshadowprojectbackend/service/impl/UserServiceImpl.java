package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.UserDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.UserDto;
import lk.ijse.greenshadowprojectbackend.entity.UserEntity;
import lk.ijse.greenshadowprojectbackend.service.UserService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;
    @Autowired
    private Mapping userMapping;

    @Override
    public UserDto save(UserDto dto) {
        dto.setId(AppUtil.generateUserId());
        return userMapping.toUserDto(userDao.save(userMapping.toUserEntity(dto)));
    }
    @Override
    public UserDto update(String id, UserDto dto) {
        return userMapping.toUserDto(userDao.save(userMapping.toUserEntity(dto)));
    }
    @Override
    public void delete(String id) {
        userDao.deleteById(id);
    }
    @Override
    public UserDto findById(String id) {
        return null;
    }
    @Override
    public List<UserDto> findAll() {
        return userMapping.asUserDtoList(userDao.findAll());
    }
    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<UserEntity> byEmail = userDao.findByEmail(email);
        return byEmail.map(userMapping::toUserDto);
    }
}

