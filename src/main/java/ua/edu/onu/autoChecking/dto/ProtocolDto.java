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

    private Long autoId;

    private Date prepDate;

    private Boolean status;

    private Long dueDate;

    private Long driverId;

    private Long tokenNumber;
}
