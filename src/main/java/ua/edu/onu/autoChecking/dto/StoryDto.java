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
public class StoryDto {
    private String vehicleIdNumber;

    private String driverPassport;

    private Date startDate;

    private String userPassport;

    private Date finishDate;
}
