package ua.edu.onu.autoChecking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicemanDto {
    private Long id;

    private String rank;

    private String name;

    private String surname;

    private String patronymic;
}
