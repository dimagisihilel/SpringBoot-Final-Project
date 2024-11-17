package lk.ijse.greenshadowprojectbackend.dto.impl;

import lk.ijse.greenshadowprojectbackend.dto.UserStatus;
import lk.ijse.greenshadowprojectbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus {
    private String id;
    private String email;
    private String password;
    private Role role;
    private String staffId;
}