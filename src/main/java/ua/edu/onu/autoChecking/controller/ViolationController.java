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
    @ResponseBody
    public ViolationDto create(@RequestBody ViolationDto request) {
        ViolationDto response = violationService.create(request);
        log.info("CREATE one brand: {}", response);
        return response;
    }
}
