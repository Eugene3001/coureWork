package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.StoryDto;
import ua.edu.onu.autoChecking.service.StoryService;

import java.util.LinkedList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/stories/main")
    public String list(Model model) {
        List<StoryDto> list = storyService.list();
        log.info("GET all stories: {}", list);

        model.addAttribute("list", list);
        return "stories/stories";
    }

    @GetMapping("/stories/create")
    public String createPage(Model model) {
        return "stories/stories-create";
    }

    @PostMapping("/stories/create")
    public String create(@ModelAttribute StoryDto request, Model model) {
        StoryDto response = storyService.create(request);
        log.info("CREATE one story: {}", response);
        return "redirect:/api/stories/main";
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
