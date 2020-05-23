package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.service.DriverService;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public List<DriverDto> list() {
        List<DriverDto> list = driverService.list();
        log.info("GET all drivers: {}", list);
        return list;
    }

    @PostMapping("/drivers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DriverDto create(@RequestBody DriverDto request) {
        DriverDto response = driverService.create(request);
        log.info("CREATE one driver: {}", response);
        return response;
    }

    @PutMapping("/drivers")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody DriverDto request) {
        driverService.update(request);
        log.info("UPDATE one driver");
    }

    @DeleteMapping("/drivers")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody DriverDto request) {
        driverService.delete(request);
        log.info("DELETE one driver");
    }

    @GetMapping("/drivers/byBirthDate")
    public List<DriverDto> birthDateSortedList() {
        List<DriverDto> list = driverService.birthDateSortedList();
        log.info("GET all drivers asc (birth date): {}", list);
        return list;
    }

    @GetMapping("/drivers/find")
    public List<DriverDto> findByCriteria(@RequestParam(required = false)
                                          @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                  Date begin,
                                          @RequestParam(required = false)
                                          @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                  Date end,
                                          @RequestParam(required = false)
                                                  String city,
                                          @RequestParam(required = false)
                                                  String street,
                                          @RequestParam(required = false)
                                                  String house,
                                          @RequestParam(required = false)
                                                  Long flat,
                                          @RequestParam(required = false)
                                                  String name,
                                          @RequestParam(required = false)
                                                  String surname,
                                          @RequestParam(required = false)
                                                  String patronymic) {
        List<DriverDto> response = driverService.findByCriteria(begin, end, city,
                                                                street, house, flat,
                                                                name, surname, patronymic);
        log.info("GET all cars by criteria: {}", response);
        return response;
    }
}
