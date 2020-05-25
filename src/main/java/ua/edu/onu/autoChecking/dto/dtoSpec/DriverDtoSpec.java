package ua.edu.onu.autoChecking.dto.dtoSpec;

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
public class DriverDtoSpec {
    private String name;//

    private String surname;//

    private String patronymic;//

    private String city;//

    private String street;//

    private String house;//

    private String flat;//

    private String birthDate;//

    private String passport;//

    private String licenseNumber;//

    private String count;
}
