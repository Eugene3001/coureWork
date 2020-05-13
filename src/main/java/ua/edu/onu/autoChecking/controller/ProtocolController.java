package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Protocol;
import ua.edu.onu.autoChecking.dao.repositories.ProtocolRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProtocolController {
    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolController(ProtocolRepository protocolRepository) { this.protocolRepository = protocolRepository; }

    @GetMapping("/protocol")
    public Iterable<Protocol> list() {
        Iterable<Protocol> list = protocolRepository.findAll();
        log.info("GET all protocols: {}", list);
        return list;
    }

    @PostMapping("/protocol")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Protocol create(@RequestBody Protocol request) {
        Protocol response = protocolRepository.save(request);
        log.info("CREATE one protocol: {}", response);
        return response;
    }
}
