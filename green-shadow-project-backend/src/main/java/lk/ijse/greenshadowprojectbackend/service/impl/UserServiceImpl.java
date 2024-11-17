package lk.ijse.greenshadowprojectbackend.service.impl;

import lk.ijse.greenshadowprojectbackend.dao.UserDao;
import lk.ijse.greenshadowprojectbackend.dto.impl.UserDto;
import lk.ijse.greenshadowprojectbackend.service.UserService;
import lk.ijse.greenshadowprojectbackend.util.AppUtil;
import lk.ijse.greenshadowprojectbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }
    @Override
    public void delete(String id) {
    }
    @Override
    public UserDto findById(String id) {
        return null;
    }
    @Override
    public List<UserDto> findAll() {
        return userMapping.asUserDtoList(userDao.findAll());
    }
}

