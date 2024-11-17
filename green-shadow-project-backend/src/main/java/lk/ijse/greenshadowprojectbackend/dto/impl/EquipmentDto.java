package lk.ijse.greenshadowprojectbackend.dto.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto {
    private String equipmentId;
    private String type;
    private String name;
    private String status;
    private String fieldId;
    private String staffId;
}