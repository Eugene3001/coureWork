package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.PolicemanDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.PolicemanDtoSpec;
import ua.edu.onu.autoChecking.service.PolicemanService;

import java.util.List;

@Controller
@Slf4j
public class PolicemanController {
    private final PolicemanService policemanService;

    @Autowired
    public PolicemanController(PolicemanService policemanService) {
        this.policemanService = policemanService;
    }

    @GetMapping("/policemen/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String list(Model model) {
        List<PolicemanDto> list = policemanService.list();
        log.info("GET all policemen: {}", list);

        model.addAttribute("list", list);
        return "policemen/policemen";
    }

    @GetMapping("/policemen/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String createPage(Model model) {
        return "policemen/policemen-create";
    }

    @PostMapping("/policemen/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String create(@ModelAttribute PolicemanDto request, Model model) {
        PolicemanDto response = policemanService.create(request);
        log.info("CREATE one policeman: {}", response);
        return "redirect:/policemen/main";
    }

    @GetMapping("/policemen/edit/{id}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String editPage(@PathVariable("id") Long id, Model model) {
        PolicemanDto policeman = policemanService.findOne(id);

        model.addAttribute("policeman", policeman);
        return "/policemen/policemen-edit";
    }

    @PostMapping("/policemen/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String update(@ModelAttribute PolicemanDto request, Model model) {
        policemanService.update(request);
        log.info("UPDATE one policeman");
        return "redirect:/policemen/main";
    }

    @GetMapping("/policemen/delete/{id}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("id") Long id, Model model) {
        PolicemanDto policemanDto = policemanService.findOne(id);
        policemanService.delete(policemanDto);
        log.info("DELETE one policeman");
        return "redirect:/policemen/main";
    }

//    @GetMapping("/policemen/find")
//    public List<PolicemanDto> findByCriteria(@RequestParam(required = false)
//                                                     String rank,
//                                             @RequestParam(required = false)
//                                                     String name,
//                                             @RequestParam(required = false)
//                                                     String surname,
//                                             @RequestParam(required = false)
//                                                     String patronymic) {
//        List<PolicemanDto> response = policemanService.findByCriteria(rank, name, surname, patronymic);
//        log.info("GET all policemen by criteria: {}", response);
//        return response;
//    }

    @GetMapping("/policemen/labQueries/1")
    public List<PolicemanDtoSpec> labQuery4_1(@RequestParam Long beginDriverYear,
                                              @RequestParam Long endDriverYear,
                                              @RequestParam Long autoYear) {
        List<PolicemanDtoSpec> response = policemanService.labQuery4_1(beginDriverYear, endDriverYear, autoYear);
        log.info("LAB QUERY 4.1");
        return response;
    }
}
