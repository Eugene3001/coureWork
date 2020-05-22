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
public class ProtocolDto {
    private Long id;

    private String vehicleIdNumber;

    private Date prepDate;

    private Boolean status;

    private String violationName;

    private Long dueDate;

    private String driverPassport;

    private Long tokenNumber;
}
