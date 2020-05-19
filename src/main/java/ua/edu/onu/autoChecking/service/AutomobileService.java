package ua.edu.onu.autoChecking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Color;
import ua.edu.onu.autoChecking.dao.entities.Model;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;
import ua.edu.onu.autoChecking.dao.repositories.ColorRepository;
import ua.edu.onu.autoChecking.dao.repositories.ModelRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.AutomobileSpec;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class AutomobileService {
    private final AutomobileRepository automobileRepository;
    private ColorRepository colorRepository;
    private ModelRepository modelRepository;

    @Autowired
    public AutomobileService(AutomobileRepository automobileRepository, ColorRepository colorRepository, ModelRepository modelRepository) {
        this.automobileRepository = automobileRepository;
        this.colorRepository = colorRepository;
        this.modelRepository = modelRepository;
    }

    private final Function<Automobile, AutomobileDto> automobileToDto = entity -> AutomobileDto.builder()
            .autoId(entity.getAutoId())
            .color(entity.getColor().getColorId())
            .engineNumber(entity.getEngineNumber())
            .modelId(entity.getModel().getModelId())
            .registrationDate(entity.getRegistrationDate())
            .vehicleIdNumber(entity.getVehicleIdNumber())
            .registrationNumber(entity.getRegistrationNumber())
            .build();

    private final Function<AutomobileDto, Automobile> dtoToAutomobile = dto -> Automobile.builder()
            .autoId(dto.getAutoId())
            .color(colorRepository.findById(dto.getColor()).orElseThrow(() -> NotFoundException.returnNotFoundEntity(dto, Color.class.getName())))
            .engineNumber(dto.getEngineNumber())
            .model(modelRepository.findById(dto.getModelId()).orElseThrow(() -> NotFoundException.returnNotFoundEntity(dto, Model.class.getName())))
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
        automobileRepository.getDateSortedListAsc()
                .forEach(automobile -> response.add(automobileToDto.apply(automobile)));
        return response;
    }

    public List<AutomobileDto> findByCriteria(String colorName, Date begin, Date end) {
        List<AutomobileDto> response = new LinkedList<>();
        Color color = colorRepository.findByColorName(colorName).orElse(null);

        automobileRepository.findAll(AutomobileSpec.buildSearchSpec(color, begin, end))
                .forEach(automobile ->
                        response.add(automobileToDto.apply(automobile))
                );

        return response;
    }
}
