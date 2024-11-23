package lk.ijse.greenshadowprojectbackend.dto.impl;

import lk.ijse.greenshadowprojectbackend.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto implements FieldStatus {
    private String fieldId;
    private String name;
    private String location;
    private Double size;
    private String image1;
    private String image2;
    private Set<String> staffIds; // Many-to-many relationship with Staff
    //private Set<CropDto> crops; // One-to-many relationship with Crop

}
