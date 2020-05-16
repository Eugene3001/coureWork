package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dao.repositories.DriverRepository;
import ua.edu.onu.autoChecking.dto.DriverDto;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    private final Function<Driver, DriverDto> driverToDto = entity -> DriverDto.builder()
            .birthDate(entity.getBirthDate())
            .city(entity.getCity())
            .flat(entity.getFlat())
            .house(entity.getHouse())
            .licenseNumber(entity.getLicenseNumber())
            .name(entity.getName())
            .passport(entity.getPassport())
            .patronymic(entity.getPatronymic())
            .street(entity.getStreet())
            .surname(entity.getSurname())
            .build();

    private final Function<DriverDto, Driver> dtoToDriver = dto -> Driver.builder()
            .birthDate(dto.getBirthDate())
            .city(dto.getCity())
            .flat(dto.getFlat())
            .house(dto.getHouse())
            .licenseNumber(dto.getLicenseNumber())
            .name(dto.getName())
            .passport(dto.getPassport())
            .patronymic(dto.getPatronymic())
            .street(dto.getStreet())
            .surname(dto.getSurname())
            .build();

    public List<DriverDto> list() {
        List<DriverDto> response = new LinkedList<>();
        driverRepository.findAll().forEach(driver -> response.add(driverToDto.apply(driver)));
        return response;
    }

    public DriverDto create(DriverDto driverDto) {
        DriverDto response = driverToDto.apply(driverRepository.save(dtoToDriver.apply(driverDto)));
        return response;
    }
}
