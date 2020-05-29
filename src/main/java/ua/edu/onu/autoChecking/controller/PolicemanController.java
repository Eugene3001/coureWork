package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dto.PolicemanDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.PolicemanDtoSpec;
import ua.edu.onu.autoChecking.service.PolicemanService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class PolicemanController {
    private final PolicemanService policemanService;

    @Autowired
    public PolicemanController(PolicemanService policemanService) {
        this.policemanService = policemanService;
    }

    @GetMapping("/policemen/main")
    public String list(Model model) {
        List<PolicemanDto> list = policemanService.list();
        log.info("GET all policemen: {}", list);

        model.addAttribute("list", list);
        return "policemen/policemen";
    }

    @GetMapping("/policemen/create")
    public String createPage(Model model) {
        return "policemen/policemen-create";
    }

    @PostMapping("/policemen/create")
    public String create(@ModelAttribute PolicemanDto request, Model model) {
        PolicemanDto response = policemanService.create(request);
        log.info("CREATE one policeman: {}", response);
        return "redirect:/api/policemen/main";
    }

    @GetMapping("/policemen/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        PolicemanDto policeman = policemanService.findOne(id);

        model.addAttribute("policeman", policeman);
        return "/policemen/policemen-edit";
    }

    @PostMapping("/policemen/edit")
    public String update(@ModelAttribute PolicemanDto request, Model model) {
        policemanService.update(request);
        log.info("UPDATE one policeman");
        return "redirect:/api/policemen/main";
    }

    @GetMapping("/policemen/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        PolicemanDto policemanDto = policemanService.findOne(id);
        policemanService.delete(policemanDto);
        log.info("DELETE one policeman");
        return "redirect:/api/policemen/main";
    }

    @GetMapping("/policemen/find")
    public List<PolicemanDto> findByCriteria(@RequestParam(required = false)
                                                     String rank,
                                             @RequestParam(required = false)
                                                     String name,
                                             @RequestParam(required = false)
                                                     String surname,
                                             @RequestParam(required = false)
                                                     String patronymic) {
        List<PolicemanDto> response = policemanService.findByCriteria(rank, name, surname, patronymic);
        log.info("GET all policemen by criteria: {}", response);
        return response;
    }

    @GetMapping("/policemen/labQueries/1")
    public List<PolicemanDtoSpec> labQuery4_1(@RequestParam Long beginDriverYear,
                                              @RequestParam Long endDriverYear,
                                              @RequestParam Long autoYear) {
        List<PolicemanDtoSpec> response = policemanService.labQuery4_1(beginDriverYear, endDriverYear, autoYear);
        log.info("LAB QUERY 4.1");
        return response;
    }
}
