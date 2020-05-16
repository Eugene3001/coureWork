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
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class AutomobileController {
    private final AutomobileRepository automobileRepository;

    @Autowired
    public AutomobileController(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    @GetMapping("/autos")
    public Iterable<Automobile> list() {
        Iterable<Automobile> list = automobileRepository.findAll();
        log.info("GET all cars: {}", list);
        return list;
    }

    @PostMapping("/autos")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Automobile create(@RequestBody Automobile request) {
        Automobile response = automobileRepository.save(request);
        log.info("CREATE one car: {}", response);
        return response;
    }
}
