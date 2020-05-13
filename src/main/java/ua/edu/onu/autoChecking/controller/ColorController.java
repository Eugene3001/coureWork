package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Color;
import ua.edu.onu.autoChecking.dao.repositories.ColorRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class ColorController {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorController(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @GetMapping("/colors")
    public Iterable<Color> list() {
        Iterable<Color> list = colorRepository.findAll();
        log.info("GET all colors: {}", list);
        return list;
    }

    @PostMapping("/colors")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Color create(@RequestBody Color request) {
        Color response = colorRepository.save(request);
        log.info("CREATE one color: {}", response);
        return response;
    }
}
