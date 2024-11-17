package lk.ijse.greenshadowprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDto {
    private String logId;
    private String logDetails;
    private Date date;
    private String image2;
    // IDs of associated entities for a lightweight representation
    private Set<String> staffIds;   // IDs of staff members monitoring this log
    private Set<Long> fieldIds;     // IDs of fields related to this log
    private Set<Long> cropIds;      // IDs of crops associated with this log
}
