package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.onu.autoChecking.dto.ColorDto;
import ua.edu.onu.autoChecking.service.ColorService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors")
    public List<ColorDto> list() {
        List<ColorDto> response = colorService.list();
        log.info("GET all colors: {}", response);
        return response;
    }

    @PostMapping("/colors")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ColorDto create(@RequestBody ColorDto request) {
        ColorDto response = colorService.create(request);
        log.info("CREATE one color: {}", response);
        return response;
    }

    @DeleteMapping("/colors")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody ColorDto request) {
        colorService.delete(request);
        log.info("DELETE one color");
    }
}
