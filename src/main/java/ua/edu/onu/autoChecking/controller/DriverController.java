package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.service.DriverService;

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
    @ResponseBody
    public DriverDto create(@RequestBody DriverDto request) {
        DriverDto response = driverService.create(request);
        log.info("CREATE one driver: {}", response);
        return response;
    }
}
