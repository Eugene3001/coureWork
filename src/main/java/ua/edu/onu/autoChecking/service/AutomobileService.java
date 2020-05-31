package ua.edu.onu.autoChecking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Color;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;
import ua.edu.onu.autoChecking.dao.repositories.ColorRepository;
import ua.edu.onu.autoChecking.dao.repositories.ModelRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.AutomobileSpec;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class AutomobileService {
    private AutomobileRepository automobileRepository;
    private ColorRepository colorRepository;
    private ModelRepository modelRepository;

    @Autowired
    public AutomobileService(AutomobileRepository automobileRepository, ColorRepository colorRepository, ModelRepository modelRepository) {
        this.automobileRepository = automobileRepository;
        this.colorRepository = colorRepository;
        this.modelRepository = modelRepository;
    }

    private final Function<Automobile, AutomobileDto> automobileToDto = entity -> AutomobileDto.builder()
            .color(entity.getColor().getColorName())
            .engineNumber(entity.getEngineNumber())
            .registrationDate(entity.getRegistrationDate())
            .vehicleIdNumber(entity.getVehicleIdNumber())
            .registrationNumber(entity.getRegistrationNumber())
            .modelName(entity.getModel().getModelName())
            .autoId(entity.getAutoId())
            .build();

    private final Function<AutomobileDto, Automobile> dtoToAutomobile = dto -> Automobile.builder()
            .autoId(automobileRepository.getAutoIdByVehicleIdNumber(dto.getVehicleIdNumber()))
            .color(colorRepository.findByColorName(dto.getColor()).orElseThrow(NotFoundException::new))
            .engineNumber(dto.getEngineNumber())
            .model(modelRepository.findByModelName(dto.getModelName()).orElseThrow(NotFoundException::new))
            .registrationDate(dto.getRegistrationDate())
            .registrationNumber(dto.getRegistrationNumber())
            .vehicleIdNumber(dto.getVehicleIdNumber())
            .build();

    public List<AutomobileDto> list() {
        List<AutomobileDto> response = new LinkedList<>();
        automobileRepository.findAll().forEach(automobile -> response.add(automobileToDto.apply(automobile)));
        return response;
    }

    public AutomobileDto findOne(Long id) {
        Automobile automobile = automobileRepository.findById(id).orElseThrow(NotFoundException::new);
        return  automobileToDto.apply(automobile);
    }

    public AutomobileDto create(AutomobileDto automobileDto) {
        Automobile automobile = dtoToAutomobile.apply(automobileDto);
        automobileRepository.save(automobile);
        return automobileToDto.apply(automobile);
    }

    public void update(AutomobileDto automobileDto) {
        Automobile automobile = automobileRepository.findByVehicleIdNumber(automobileDto.getVehicleIdNumber())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Automobile.class));

        automobile.setColor(colorRepository.findByColorName(automobileDto.getColor())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Automobile.class)));

        automobile.setEngineNumber(automobileDto.getEngineNumber());
        automobile.setModel(modelRepository.findByModelName(automobileDto.getModelName())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Automobile.class)));

        automobile.setRegistrationDate(automobileDto.getRegistrationDate());
        automobile.setRegistrationNumber(automobileDto.getRegistrationNumber());

        automobileRepository.save(automobile);
    }

    public void delete(AutomobileDto automobileDto) {
        Automobile automobile = automobileRepository.findByVehicleIdNumber(automobileDto.getVehicleIdNumber())
                .orElseThrow(() -> NotFoundException.notFoundWhenDelete(Automobile.class));

        automobileRepository.deleteById(automobile.getAutoId());
    }

    public List<AutomobileDto> registrationDateSortedList() {
        List<AutomobileDto> response = new LinkedList<>();

        automobileRepository.getDateSortedListAsc()
                .forEach(automobile -> response.add(automobileToDto.apply(automobile)));

        return response;
    }

//    public List<AutomobileDto> findByCriteria(String brandName) {
//        List<AutomobileDto> response = new LinkedList<>();
//
//        automobileRepository.findByBrandName(brandName)
//                .forEach(automobile -> response.add(automobileToDto.apply(automobile)));
//
//        return response;
//    }

    public Long labQuery3_3(String brandName, String bodyType, Long yearBegin, Long yearEnd) {
        Long count = automobileRepository.countByBrandAndBodyTypeAndPeriod(brandName, bodyType, yearBegin, yearEnd);
        return count;
    }

    public List<AutomobileDto> labQuery4_3() {
        List<String> data = automobileRepository.selectByProtocolFlagEqualNull();
        List<AutomobileDto> response = new LinkedList<>();

        data.forEach(item -> {
            String[] elements = item.split(",");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                response.add(new AutomobileDto(
                        Long.valueOf(elements[0]),
                        elements[1],
                        dateFormat.parse(elements[2]),
                        elements[3],
                        elements[4],
                        elements[5],
                        elements[6]
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return response;
    }
}
