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
public class ProtocolDto {
    private Long id;

    private String vehicleIdNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date prepDate;

    private Boolean status;

    private String violationName;

    private Long dueDate;

    private String driverPassport;

    private Long tokenNumber;
}
