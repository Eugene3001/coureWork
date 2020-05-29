package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.dto.ViolationDto;
import ua.edu.onu.autoChecking.service.ViolationService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class ViolationController {
    private final ViolationService violationService;

    @Autowired
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/violations/main")
    public String list(Model model) {
        List<ViolationDto> list = violationService.list();
        log.info("GET all violations: {}", list);

        model.addAttribute("list", list);

        return "violations/violations";
    }

    @GetMapping("/violations/create")
    public String createPage(Model model) {
        return "violations/violations-create";
    }

    @PostMapping("/violations/create")
    public String create(@ModelAttribute ViolationDto request, Model model) {
        ViolationDto response = violationService.create(request);
        log.info("CREATE one brand: {}", response);
        return "redirect:/api/violations/main";
    }

    @GetMapping("/violations/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        ViolationDto violation = violationService.findOne(id);

        model.addAttribute("violation", violation);
        return "/violations/violations-edit";
    }

    @PostMapping("/violations/edit")
    public String update(@ModelAttribute ViolationDto request, Model model) {
        violationService.update(request);
        log.info("UPDATE one violation");
        return "redirect:/api/violations/main";
    }

    @GetMapping("/violations/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        ViolationDto violationDto = violationService.findOne(id);
        violationService.delete(violationDto);
        log.info("DELETE one violation");
        return "redirect:/api/violations/main";
    }

    @GetMapping("/violations/find")
    public String findByCriteria(@RequestParam Float first,
                                 @RequestParam Float second,
                                 @RequestParam String isCourt,
                                 @RequestParam String isNotCourt,
                                 Model model) {
        List<ViolationDto> response = violationService.findByCriteria(first, second, isCourt, isNotCourt);
        log.info("GET all violations by criteria: {}", response);

        model.addAttribute("list", response);
        return "violations/violations";
    }

    @GetMapping("/violations/labQueries/1")
    public List<ViolationDto> selectSeveralMostPopularViolations(@RequestParam Long count) {
        List<ViolationDto> response = violationService.labQuery3_6(count);
        log.info("LAB QUERY 3.6");
        return response;
    }
}
