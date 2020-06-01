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
public class DriverDto {
    private Long driverId;

    private String passport;

    private String licenseNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String city;

    private String street;

    private String house;

    private Long flat;

    private String name;

    private String surname;

    private String patronymic;
}
