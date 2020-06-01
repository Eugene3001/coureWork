package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.dto.ColorDto;
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.service.AutomobileService;
import ua.edu.onu.autoChecking.service.ColorService;
import ua.edu.onu.autoChecking.service.ModelService;

import java.util.List;

@Controller
@Slf4j
public class AutomobileController {
    private AutomobileService automobileService;
    private ModelService modelService;
    private ColorService colorService;

    @Autowired
    public AutomobileController(AutomobileService automobileService, ModelService modelService, ColorService colorService) {
        this.automobileService = automobileService;
        this.modelService = modelService;
        this.colorService = colorService;
    }

    @GetMapping("/autos/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String list(Model model) {
        List<AutomobileDto> list = automobileService.list();
        log.info("GET all cars: {}", list);

        model.addAttribute("title", "List of cars registered");
        model.addAttribute("list", list);

        return "autos/autos";
    }

    @GetMapping("/autos/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String createPage(Model model) {
        AutomobileDto automobile = new AutomobileDto();
        List<ModelDto> modelDtoList = modelService.list();
        List<ColorDto> colorDtoList = colorService.list();

        model.addAttribute("modelList", modelDtoList);
        model.addAttribute("colorList", colorDtoList);
        model.addAttribute("automobile", automobile);
        return "autos/autos-create";
    }

    @PostMapping("/autos/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String create(@ModelAttribute AutomobileDto request, Model model) {
        AutomobileDto response = automobileService.create(request);
        log.info("CREATE one car: {}", response);
        return "redirect:/autos/main";
    }

    @GetMapping("/autos/edit/{autoId}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String editPage(@PathVariable("autoId") Long autoId, Model model) {
        AutomobileDto automobile = automobileService.findOne(autoId);
        List<ModelDto> modelDtoList = modelService.list();
        List<ColorDto> colorDtoList = colorService.list();

        model.addAttribute("modelList", modelDtoList);
        model.addAttribute("colorList", colorDtoList);
        model.addAttribute("automobile", automobile);
        return "/autos/autos-edit";
    }

    @PostMapping("/autos/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String update(@ModelAttribute AutomobileDto request, Model model) {
        automobileService.update(request);
        log.info("UPDATE one car");
        return "redirect:/autos/main";
    }

    @GetMapping("/autos/delete/{autoId}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("autoId") Long autoId, Model model) {
        AutomobileDto automobileDto = automobileService.findOne(autoId);
        automobileService.delete(automobileDto);
        log.info("DELETE one car");
        return "redirect:/autos/main";
    }

//    @GetMapping("/autos/sortByDate")
//    public List<AutomobileDto> registrationDateSortedList() {
//        List<AutomobileDto> list = automobileService.registrationDateSortedList();
//        log.info("GET all cars asc (registration date): {}", list);
//        return list;
//    }

//    @GetMapping("/autos/find/{brandName}")
//    public String findByCriteria(@PathVariable String modelName,
//                                 Model model) {
//        List<AutomobileDto> response = automobileService.findByCriteria(modelName);
//        log.info("GET all cars by criteria: {}", response);
//
//        model.addAttribute("list", response);
//        return "autos/autos";
//    }

//    @GetMapping("/autos/labQueries/1")
//    public Long countByBrandAndBodyTypeAndPeriod(
//            @RequestParam String brandName,
//            @RequestParam String bodyType,
//            @RequestParam Long yearBegin,
//            @RequestParam Long yearEnd
//    ) {
//        Long count = automobileService.labQuery3_3(brandName, bodyType, yearBegin, yearEnd);
//        log.info("COUNT is {}", count);
//        return count;
//    }

    @GetMapping("/autos/labQueries/2")
    public List<AutomobileDto> labQuery4_3() {
        List<AutomobileDto> response = automobileService.labQuery4_3();
        log.info("LAB QUERY 4.3");
        return response;
    }
}
