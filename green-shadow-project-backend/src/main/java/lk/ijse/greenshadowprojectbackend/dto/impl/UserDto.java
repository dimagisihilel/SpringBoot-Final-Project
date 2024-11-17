package lk.ijse.greenshadowprojectbackend.dto.impl;

import lk.ijse.greenshadowprojectbackend.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus {
    private String id;
    private String username;
    private String email;
    private String staffId;
}