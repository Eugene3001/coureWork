package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.repositories.StoryRepository;
import ua.edu.onu.autoChecking.dto.StoryDto;
import ua.edu.onu.autoChecking.service.StoryService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/stories")
    public List<StoryDto> list() {
        List<StoryDto> list = storyService.list();
        log.info("GET all stories: {}", list);
        return list;
    }

    @PostMapping("/stories")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public StoryDto create(@RequestBody StoryDto request) {
        StoryDto response = storyService.create(request);
        log.info("CREATE one story: {}", response);
        return response;
    }
}
