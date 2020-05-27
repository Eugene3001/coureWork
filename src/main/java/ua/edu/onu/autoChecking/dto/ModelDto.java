package ua.edu.onu.autoChecking.dto;

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
public class ModelDto {
    private String modelName;

    private String brandName;

    private String bodyType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date manufYear;
}
