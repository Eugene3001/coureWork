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
import ua.edu.onu.autoChecking.dto.PolicemanDto;
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
    @ResponseBody
    public PolicemanDto create(@RequestBody PolicemanDto request) {
        PolicemanDto response = policemanService.create(request);
        log.info("CREATE one policeman: {}", response);
        return response;
    }
}
