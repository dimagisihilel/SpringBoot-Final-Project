package lk.ijse.greenshadowprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDto {
    private Long id;
    private String commonName;
    private String specificName;
    private String category;
    private String season;
    private String image1;
    private String fieldId;
}
