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
public class StoryDto {
    private Long autoId;

    private Long driverId;

    private String vehicleIdNumber;

    private String driverPassport;

    private String startDate;

    private String userPassport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
}
