package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dao.repositories.DriverRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.DriverSpec;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.DriverDtoSpec;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.Date;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.List;

@Service
public class DriverService {
    private DriverRepository driverRepository;

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
            .driverId(driverRepository.getDriverIdByPassport(dto.getPassport()))
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
        return driverToDto.apply(driverRepository.save(dtoToDriver.apply(driverDto)));
    }

    public void update(DriverDto driverDto) {
        Driver driver = driverRepository.findByPassport(driverDto.getPassport())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Driver.class));

        driver.setBirthDate(driverDto.getBirthDate());
        driver.setCity(driverDto.getCity());
        driver.setFlat(driverDto.getFlat());
        driver.setHouse(driverDto.getHouse());
        driver.setLicenseNumber(driverDto.getLicenseNumber());
        driver.setName(driverDto.getName());
        driver.setSurname(driverDto.getSurname());
        driver.setPatronymic(driverDto.getPatronymic());
        driver.setStreet(driverDto.getStreet());

        driverRepository.save(driver);
    }

    public void delete(DriverDto driverDto) {
        Driver driver = driverRepository.findByPassport(driverDto.getPassport())
                .orElseThrow(() -> NotFoundException.notFoundWhenDelete(Driver.class));

        driverRepository.deleteById(driver.getDriverId());
    }

    public List<DriverDto> birthDateSortedList() {
        List<DriverDto> response = new LinkedList<>();
        driverRepository.getBirthDateSortedList().forEach(driver -> response.add(driverToDto.apply(driver)));
        return response;
    }

    public List<DriverDto> findByCriteria(Date begin, Date end, String city,
                                          String street, String house, Long flat,
                                          String name, String surname, String patronymic) {
        List<DriverDto> response = new LinkedList<>();

        driverRepository.findAll(DriverSpec.buildSearchSpec(begin, end, city,
                                 street, house, flat,
                                 name, surname, patronymic))
                .forEach(driver ->
                        response.add(driverToDto.apply(driver))
                );

        return response;
    }

    public List<DriverDto> labQuery3_5() {
        List<DriverDto> response = new LinkedList<>();
        driverRepository.selectByPastDuePaymentDate().forEach(driver -> response.add(driverToDto.apply(driver)));
        return response;
    }

    public List<DriverDtoSpec> labQuery4_2() {
        List<String> data = driverRepository.firstView();
        List<DriverDtoSpec> objects = new LinkedList<>();

        data.forEach(item -> {
            String[] elements = item.split(",");
            objects.add(new DriverDtoSpec(
                    elements[0],
                    elements[1],
                    elements[2],
                    elements[3],
                    elements[4],
                    elements[5],
                    elements[6],
                    elements[7],
                    elements[8],
                    elements[9],
                    elements[10]
            ));
        });

        return objects;
    }
}
