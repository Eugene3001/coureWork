package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dao.repositories.DriverRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class DriverController {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/drivers")
    public Iterable<Driver> list() {
        Iterable<Driver> list = driverRepository.findAll();
        log.info("GET all drivers: {}", list);
        return list;
    }

    @PostMapping("/drivers")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Driver create(@RequestBody Driver request) {
        log.info(request.getLicenseNumber());
        Driver response = driverRepository.save(request);
        log.info("CREATE one driver: {}", response);
        return response;
    }
}
