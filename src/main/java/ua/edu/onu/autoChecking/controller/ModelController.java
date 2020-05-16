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
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.service.ModelService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models")
    public List<ModelDto> list() {
        List<ModelDto> list = modelService.list();
        log.info("GET all models: {}", list);
        return list;
    }

    @PostMapping("/models")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public ModelDto create(@RequestBody ModelDto request) {
        ModelDto response = modelService.create(request);
        log.info("CREATE one model: {}", response);
        return response;
    }
}
