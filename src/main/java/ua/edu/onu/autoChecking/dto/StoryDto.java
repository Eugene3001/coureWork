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
    private Long autoId;

    private Long driverId;

    private Date startDate;

    private String userPassport;

    private Date finishDate;
}
