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
public class ModelDto {
    private Long modelId;

    private String modelName;

    private Long brandId;

    private String bodyType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date manufYear;
}
