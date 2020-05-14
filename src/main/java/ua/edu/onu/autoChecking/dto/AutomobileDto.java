package ua.edu.onu.autoChecking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomobileDto {
    private Long autoId;

    private String vehicleIdNumber;

    private Date registrationDate;

    private Long modelId;

    private Long color;

    private String engineNumber;

    private String registrationNumber;
}
