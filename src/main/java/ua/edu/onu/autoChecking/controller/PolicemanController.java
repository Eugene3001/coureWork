package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dto.PolicemanDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.PolicemanDtoSpec;
import ua.edu.onu.autoChecking.service.PolicemanService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class PolicemanController {
    private final PolicemanService policemanService;

    @Autowired
    public PolicemanController(PolicemanService policemanService) {
        this.policemanService = policemanService;
    }

    @GetMapping("/policemen")
    public List<PolicemanDto> list() {
        List<PolicemanDto> list = policemanService.list();
        log.info("GET all policemen: {}", list);
        return list;
    }

    @PostMapping("/policemen")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PolicemanDto create(@RequestBody PolicemanDto request) {
        PolicemanDto response = policemanService.create(request);
        log.info("CREATE one policeman: {}", response);
        return response;
    }

    @PutMapping("/policemen")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody PolicemanDto request) {
        policemanService.update(request);
        log.info("UPDATE one policeman");
    }

    @DeleteMapping("/policemen")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(PolicemanDto request) {
        policemanService.delete(request);
        log.info("DELETE one policeman");
    }

    @GetMapping("/policemen/find")
    public List<PolicemanDto> findByCriteria(@RequestParam(required = false)
                                                     String rank,
                                             @RequestParam(required = false)
                                                     String name,
                                             @RequestParam(required = false)
                                                     String surname,
                                             @RequestParam(required = false)
                                                     String patronymic) {
        List<PolicemanDto> response = policemanService.findByCriteria(rank, name, surname, patronymic);
        log.info("GET all policemen by criteria: {}", response);
        return response;
    }

    @GetMapping("/policemen/labQueries/1")
    public List<PolicemanDtoSpec> labQuery4_1(@RequestParam Long beginDriverYear,
                                              @RequestParam Long endDriverYear,
                                              @RequestParam Long autoYear) {
        List<PolicemanDtoSpec> response = policemanService.labQuery4_1(beginDriverYear, endDriverYear, autoYear);
        log.info("LAB QUERY 4.1");
        return response;
    }
}
