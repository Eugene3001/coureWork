package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public StoryDto create(@RequestBody StoryDto request) {
        StoryDto response = storyService.create(request);
        log.info("CREATE one story: {}", response);
        return response;
    }

    @DeleteMapping("/stories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void delete(@RequestBody StoryDto request) {
        storyService.delete(request);
        log.info("DELETE one story");
    }

    @GetMapping("/stories/byDate")
    public List<StoryDto> datesSortedList(@RequestParam String dateType) {
        List<StoryDto> response = new LinkedList<>();

        switch (dateType) {
            case "start": response = storyService.startDateSortedList(); break;
            case "finish": response = storyService.finishDateSortedList(); break;
        }

        log.info("GET all stories by {}, {}", dateType, response);
        return response;
    }

    @GetMapping("/stories/find")
    public List<StoryDto> findByCriteria(@RequestParam(required = false)
                                                 String passport,
                                         @RequestParam(required = false)
                                                 String isOwner,
                                         @RequestParam(required = false)
                                                 String isNotOwner) {
        List<StoryDto> response = storyService.findByCriteria(passport, isOwner, isNotOwner);
        log.info("GET all stories by criteria {}", response);
        return response;
    }

    @GetMapping("/stories/labQueries/1")
    public List<StoryDto> selectByPeriodAndRegistrationNumber(@RequestParam String registrationNumber,
                                    @RequestParam Long beginYear,
                                    @RequestParam Long endYear) {
        List<StoryDto> response = storyService.labQuery3_2(registrationNumber, beginYear, endYear);
        log.info("Lab query 1");
        return response;
    }
}
