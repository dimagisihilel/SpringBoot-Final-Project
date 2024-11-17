package lk.ijse.greenshadowprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto {
    private String fieldId;
    private String name;
    private String location;
    private Double size;
    private String image1;
    private String image2;
    private Set<StaffDto> staffMembers; // Many-to-many relationship with Staff
    private Set<CropDto> crops; // One-to-many relationship with Crop
    public FieldDto(String fieldId,String name,String location,Double size,String image1,String image2){
        this.fieldId = fieldId;
        this.name =name;
        this.location=location;
        this.size=size;
        this.image1=image1;
        this.image2=image2;
    }
}