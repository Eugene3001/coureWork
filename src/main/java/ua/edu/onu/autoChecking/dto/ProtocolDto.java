package ua.edu.onu.autoChecking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolDto {
    private Long id;

    private String vehicleIdNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prepDate;

    private Boolean status;

    private String violationName;

    private Long dueDate;

    private String driverPassport;

    private Long tokenNumber;
}
