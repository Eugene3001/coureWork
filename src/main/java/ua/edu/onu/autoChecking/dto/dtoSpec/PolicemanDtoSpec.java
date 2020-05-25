package ua.edu.onu.autoChecking.dto.dtoSpec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicemanDtoSpec {
    private String tokenNumber;

    private String name;

    private String surname;

    private String patronymic;

    private String rank1;

    private String rank2;

    private String rank3;
}
