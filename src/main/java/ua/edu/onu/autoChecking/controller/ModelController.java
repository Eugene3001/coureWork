package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.service.ModelService;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models/main")
    public String list(Model model) {
        List<ModelDto> list = modelService.list();
        log.info("GET all models: {}", list);

        model.addAttribute("list", list);
        return "models/models";
    }

    @GetMapping("/models/create")
    public String createPage(Model model) {
        return "models/models-create";
    }

    @PostMapping("/models/create")
    public String create(@ModelAttribute ModelDto request, Model model) {
        ModelDto response = modelService.create(request);
        log.info("CREATE one model: {}", response);
        return "redirect:/api/models/main";
    }

    @DeleteMapping("/models")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody ModelDto request) {
        modelService.delete(request);
        log.info("DELETE one model");
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
