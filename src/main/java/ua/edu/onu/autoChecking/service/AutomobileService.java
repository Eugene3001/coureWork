package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;
import ua.edu.onu.autoChecking.dto.AutomobileDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class AutomobileService {
    private final AutomobileRepository automobileRepository;

    @Autowired
    public AutomobileService(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    private final Function<Automobile, AutomobileDto> automobileToDto = entity -> AutomobileDto.builder()
            .autoId(entity.getAutoId())
            .color(entity.getColor())
            .engineNumber(entity.getEngineNumber())
            .modelId(entity.getModelId())
            .registrationDate(entity.getRegistrationDate())
            .vehicleIdNumber(entity.getVehicleIdNumber())
            .registrationNumber(entity.getRegistrationNumber())
            .build();

    private final Function<AutomobileDto, Automobile> dtoToAutomobile = dto -> Automobile.builder()
            .autoId(dto.getAutoId())
            .color(dto.getColor())
            .engineNumber(dto.getEngineNumber())
            .modelId(dto.getModelId())
            .registrationDate(dto.getRegistrationDate())
            .registrationNumber(dto.getRegistrationNumber())
            .vehicleIdNumber(dto.getVehicleIdNumber())
            .build();

    public List<AutomobileDto> list() {
        List<AutomobileDto> response = new LinkedList<>();
        automobileRepository.findAll().forEach(automobile -> response.add(automobileToDto.apply(automobile)));
        return response;
    }

    public AutomobileDto create(AutomobileDto automobileDto) {
        return automobileToDto.apply(automobileRepository.save(dtoToAutomobile.apply(automobileDto)));
    }

    public List<AutomobileDto> registrationDateSortedList() {
        List<AutomobileDto> response = new LinkedList<>();
        automobileRepository.getDateSortedListAsc().forEach(automobile -> response.add(automobileToDto.apply(automobile)));
        return response;
    }
}
