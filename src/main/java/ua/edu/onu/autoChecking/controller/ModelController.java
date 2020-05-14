package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Model;
import ua.edu.onu.autoChecking.dao.repositories.ModelRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class ModelController {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/models")
    public Iterable<Model> list() {
        Iterable<Model> list = modelRepository.findAll();
        log.info("GET all models: {}", list);
        return list;
    }

    @PostMapping("/models")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Model create(@RequestBody Model request) {
        Model response = modelRepository.save(request);
        log.info("CREATE one model: {}", response);
        return response;
    }
}
