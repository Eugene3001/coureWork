package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dao.repositories.PolicemanRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class PolicemanController {
    private final PolicemanRepository policemanRepository;

    @Autowired
    public PolicemanController(PolicemanRepository policemanRepository) { this.policemanRepository = policemanRepository; }

    @GetMapping("/policemen")
    public Iterable<Policeman> list() {
        Iterable<Policeman> list = policemanRepository.findAll();
        log.info("GET all policemans: {}", list);
        return list;
    }

    @PostMapping("/policemen")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Policeman create(@RequestBody Policeman request) {
        Policeman response = policemanRepository.save(request);
        log.info("CREATE one policemen: {}", response);
        return response;
    }
}
