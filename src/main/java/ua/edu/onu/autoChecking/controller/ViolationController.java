package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Violation;
import ua.edu.onu.autoChecking.dao.repositories.ViolationRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class ViolationController {
    private final ViolationRepository violationRepository;

    @Autowired
    public ViolationController(ViolationRepository violationRepository) { this.violationRepository = violationRepository; }

    @GetMapping("/violations")
    public Iterable<Violation> list() {
        Iterable<Violation> list = violationRepository.findAll();
        log.info("GET all violations: {}", list);
        return list;
    }

    @PostMapping("/violations")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Violation create(@RequestBody Violation request) {
        Violation response = violationRepository.save(request);
        log.info("CREATE one brand: {}", response);
        return response;
    }
}
