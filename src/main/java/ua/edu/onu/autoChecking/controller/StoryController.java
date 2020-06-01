package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.ids.StoryId;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.dto.StoryDto;
import ua.edu.onu.autoChecking.service.AutomobileService;
import ua.edu.onu.autoChecking.service.DriverService;
import ua.edu.onu.autoChecking.service.StoryService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@Slf4j
public class StoryController {
    private StoryService storyService;
    private AutomobileService automobileService;
    private DriverService driverService;

    @Autowired
    public StoryController(StoryService storyService, AutomobileService automobileService, DriverService driverService) {
        this.storyService = storyService;
        this.automobileService = automobileService;
        this.driverService = driverService;
    }

    @GetMapping("/stories/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String list(Model model) {
        List<StoryDto> list = storyService.list();
        log.info("GET all stories: {}", list);

        model.addAttribute("list", list);
        return "stories/stories";
    }

    @GetMapping("/stories/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String createPage(Model model) {
        StoryDto storyDto = new StoryDto();
        List<AutomobileDto> automobileDtoList = automobileService.list();
        List<DriverDto> driverDtoList = driverService.list();

        model.addAttribute("automobileList", automobileDtoList);
        model.addAttribute("driverList", driverDtoList);
        model.addAttribute("story", storyDto);
        return "stories/stories-create";
    }

    @PostMapping("/stories/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String create(@ModelAttribute StoryDto request, Model model) {
        StoryDto response = storyService.create(request);
        log.info("CREATE one story: {}", response);
        return "redirect:/stories/main";
    }

    @GetMapping("/stories/edit/{autoId}/{driverId}/{startDate}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String editPage(@PathVariable("autoId")Long autoId,
                           @PathVariable("driverId") Long driverId,
                           @PathVariable("startDate") String startDate,
                           Model model) {
        StoryDto story = storyService.findOne(new StoryId(autoId, driverId, startDate));

        List<DriverDto> driverDtoList = driverService.list();
        model.addAttribute("driverList", driverDtoList);
        model.addAttribute("story", story);
        return "/stories/stories-edit";
    }

    @PostMapping("/stories/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String update(@ModelAttribute StoryDto storyDto, Model model) {
        storyService.update(storyDto);
        log.info("UPDATE one story");
        return "redirect:/stories/main";
    }

    @GetMapping("/stories/delete/{autoId}/{driverId}/{startDate}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("autoId")Long autoId,
                       @PathVariable("driverId") Long driverId,
                       @PathVariable("startDate") String startDate,
                       Model model) {
        StoryDto storyDto = storyService.findOne(new StoryId(
                autoId, driverId, startDate
        ));
        storyService.delete(storyDto);
        log.info("DELETE one story");
        return "redirect:/stories/main";
    }

//    @GetMapping("/stories/byDate")
//    public List<StoryDto> datesSortedList(@RequestParam String dateType) {
//        List<StoryDto> response = new LinkedList<>();
//
//        switch (dateType) {
//            case "start": response = storyService.startDateSortedList(); break;
//            case "finish": response = storyService.finishDateSortedList(); break;
//        }
//
//        log.info("GET all stories by {}, {}", dateType, response);
//        return response;
//    }

//    @GetMapping("/stories/find")
//    public List<StoryDto> findByCriteria(@RequestParam(required = false)
//                                                 String passport,
//                                         @RequestParam(required = false)
//                                                 String isOwner,
//                                         @RequestParam(required = false)
//                                                 String isNotOwner) {
//        List<StoryDto> response = storyService.findByCriteria(passport, isOwner, isNotOwner);
//        log.info("GET all stories by criteria {}", response);
//        return response;
//    }

//    @GetMapping("/stories/labQueries/1")
//    public List<StoryDto> selectByPeriodAndRegistrationNumber(@RequestParam String registrationNumber,
//                                    @RequestParam Long beginYear,
//                                    @RequestParam Long endYear) {
//        List<StoryDto> response = storyService.labQuery3_2(registrationNumber, beginYear, endYear);
//        log.info("Lab query 1");
//        return response;
//    }
}
