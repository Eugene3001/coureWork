package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.ViolationDto;
import ua.edu.onu.autoChecking.service.ViolationService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ViolationController {
    private final ViolationService violationService;

    @Autowired
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/violations")
    public List<ViolationDto> list() {
        List<ViolationDto> list = violationService.list();
        log.info("GET all violations: {}", list);
        return list;
    }

    @PostMapping("/violations")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ViolationDto create(@RequestBody ViolationDto request) {
        ViolationDto response = violationService.create(request);
        log.info("CREATE one brand: {}", response);
        return response;
    }

    @PutMapping("/violations")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody ViolationDto request) {
        violationService.update(request);
        log.info("UPDATE one violation");
    }

    @DeleteMapping("/violations")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void delete(@RequestParam ViolationDto request) {
        violationService.delete(request);
        log.info("DELETE one violation");
    }

    @GetMapping("/violations/find")
    public List<ViolationDto> findByCriteria(@RequestParam(required = false)
                                                     Float first,
                                             @RequestParam(required = false)
                                                     Float second,
                                             @RequestParam(required = false)
                                                     String isCourt,
                                             @RequestParam(required = false)
                                                     String isNotCourt) {
        List<ViolationDto> response = violationService.findByCriteria(first, second, isCourt, isNotCourt);
        log.info("GET all violations by criteria: {}", response);
        return response;
    }

    @GetMapping("/violations/labQueries/1")
    public List<ViolationDto> selectSeveralMostPopularViolations(@RequestParam Long count) {
        List<ViolationDto> response = violationService.labQuery3_6(count);
        log.info("LAB QUERY 3.6");
        return response;
    }
}
