package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.onu.autoChecking.dto.StoryDto;
import ua.edu.onu.autoChecking.service.StoryService;

import java.util.LinkedList;
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
        List<StoryDto> response = storyService.list();
        log.info("GET all stories: {}", response);
        return response;
    }

    @PostMapping("/stories")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public StoryDto create(@RequestBody StoryDto request) {
        StoryDto response = storyService.create(request);
        log.info("CREATE one story: {}", response);
        return response;
    }

    @GetMapping("/stories/byDate")
    @ResponseBody
    public List<StoryDto> datesSortedList(@RequestParam String dateType) {
        List<StoryDto> response = new LinkedList<>();

        switch (dateType) {
            case "start": response = storyService.startDateSortedList(); break;
            case "finish": response = storyService.finishDateSortedList(); break;
        }

        log.info("GET all stories by {}, {}", dateType, response);
        return response;
    }
}
