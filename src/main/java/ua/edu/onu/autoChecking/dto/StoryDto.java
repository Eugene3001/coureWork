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
public class StoryDto {
    private String vehicleIdNumber;

    private String driverPassport;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    private String userPassport;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date finishDate;
}
