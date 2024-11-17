package lk.ijse.greenshadowprojectbackend.dto.impl;

import lk.ijse.greenshadowprojectbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String address;
    private String contact;
    private Date joinDate;
    private Role role;
}
