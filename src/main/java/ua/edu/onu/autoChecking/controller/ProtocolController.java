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
import ua.edu.onu.autoChecking.dto.ProtocolDto;
import ua.edu.onu.autoChecking.service.ProtocolService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProtocolController {
    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @GetMapping("/protocol")
    public List<ProtocolDto> list() {
        List<ProtocolDto> list = protocolService.list();
        log.info("GET all protocols: {}", list);
        return list;
    }

    @PostMapping("/protocol")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public ProtocolDto create(@RequestBody ProtocolDto request) {
        ProtocolDto response = protocolService.create(request);
        log.info("CREATE one protocol: {}", response);
        return response;
    }
}
