package ua.edu.onu.autoChecking.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.service.AutomobileService;

import java.util.Date;
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

    @GetMapping("/autos/sortByDate")
    public List<AutomobileDto> registrationDateSortedList() {
        List<AutomobileDto> list = automobileService.registrationDateSortedList();
        log.info("GET all cars asc (registration date): {}", list);
        return list;
    }

    @GetMapping("/autos/find")
    public List<AutomobileDto> findByCriteria(@RequestParam(required = false)
                                                      String color,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                      Date begin,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                      Date end) {
        List<AutomobileDto> response = automobileService.findByCriteria(color, begin, end);
        log.info("GET all cars by criteria: {}", response);
        return response;
    }
}
