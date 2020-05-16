package ua.edu.onu.autoChecking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    private Long modelId;

    private Long color;

    private String engineNumber;

    private String registrationNumber;
}
