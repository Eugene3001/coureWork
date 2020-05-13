package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.repositories.StoryRepository;

@RestController
@Slf4j
@RequestMapping("/api")
public class StoryController {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository) { this.storyRepository = storyRepository; }

    @GetMapping("/stories")
    public Iterable<Story> list() {
        Iterable<Story> list = storyRepository.findAll();
        log.info("GET all stories: {}", list);
        return list;
    }

    @PostMapping("/stories")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Story create(@RequestBody Story request) {
        Story response = storyRepository.save(request);
        log.info("CREATE one story: {}", response);
        return response;
    }
}
