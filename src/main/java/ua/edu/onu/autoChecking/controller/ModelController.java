package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.service.ModelService;

import java.util.Date;
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

    @GetMapping("/models/byMYear")
    public List<ModelDto> manufactureYearSortedList() {
        List<ModelDto> response = modelService.manufactureYearSortedList();
        log.info("GET all models asc (manufacture year): {}", response);
        return response;
    }

    @GetMapping("/models/find")
    public List<ModelDto> findByCriteria(@RequestParam(required = false)
                                                 String bodyType,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                 Date begin,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                 Date end) {
        List<ModelDto> response = modelService.findByCriteria(bodyType, begin, end);
        log.info("GET all models by criteria: {}", response);
        return response;
    }
}
