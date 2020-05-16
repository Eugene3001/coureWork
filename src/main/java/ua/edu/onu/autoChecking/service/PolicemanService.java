package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dao.repositories.PolicemanRepository;
import ua.edu.onu.autoChecking.dto.PolicemanDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class PolicemanService {
    private final PolicemanRepository policemanRepository;

    @Autowired
    public PolicemanService(PolicemanRepository policemanRepository) {
        this.policemanRepository = policemanRepository;
    }

    private final Function<Policeman, PolicemanDto> policemanToDto = entity -> PolicemanDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .patronymic(entity.getPatronymic())
            .rank(entity.getRank())
            .surname(entity.getSurname())
            .build();

    private final Function<PolicemanDto, Policeman> dtoToPoliceman = dto -> Policeman.builder()
            .id(dto.getId())
            .name(dto.getName())
            .patronymic(dto.getPatronymic())
            .rank(dto.getRank())
            .surname(dto.getSurname())
            .build();

    public List<PolicemanDto> list() {
        List<PolicemanDto> response = new LinkedList<>();
        policemanRepository.findAll().forEach(policeman -> response.add(policemanToDto.apply(policeman)));
        return response;
    }

    public PolicemanDto create(PolicemanDto policemanDto) {
        return policemanToDto.apply(policemanRepository.save(dtoToPoliceman.apply(policemanDto)));
    }
}
