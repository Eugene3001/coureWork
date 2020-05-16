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
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.service.AutomobileService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class AutomobileController {
    private final AutomobileService automobileService;

    @Autowired
    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @GetMapping("/autos")
    public List<AutomobileDto> list() {
        List<AutomobileDto> list = automobileService.list();
        log.info("GET all cars: {}", list);
        return list;
    }

    @PostMapping("/autos")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public AutomobileDto create(@RequestBody AutomobileDto request) {
        AutomobileDto response = automobileService.create(request);
        log.info("CREATE one car: {}", response);
        return response;
    }

    @GetMapping("/autos/byDate")
    public List<AutomobileDto> registrationDateSortedList() {
        List<AutomobileDto> list = automobileService.registrationDateSortedList();
        log.info("GET all cars asc (registration date): {}", list);
        return list;
    }
}
