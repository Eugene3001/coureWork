package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.*;
import ua.edu.onu.autoChecking.service.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class ProtocolController {
    private ProtocolService protocolService;
    private AutomobileService automobileService;
    private ViolationService violationService;
    private DriverService driverService;
    private PolicemanService policemanService;

    @Autowired
    public ProtocolController(ProtocolService protocolService, AutomobileService automobileService,
                              ViolationService violationService, DriverService driverService,
                              PolicemanService policemanService
    ) {
        this.protocolService = protocolService;
        this.automobileService = automobileService;
        this.violationService = violationService;
        this.driverService = driverService;
        this.policemanService = policemanService;
    }

    @GetMapping("/protocols/main")
    public String list(Model model) {
        List<ProtocolDto> list = protocolService.list();
        log.info("GET all protocols: {}", list);

        model.addAttribute("list", list);
        return "protocols/protocols";
    }

    @GetMapping("/protocols/create")
    public String createPage(Model model) {
        ProtocolDto protocolDto = new ProtocolDto();
        getSelectionLists(model, protocolDto);
        return "protocols/protocols-create";
    }

    @PostMapping("/protocols/create")
    public String create(@ModelAttribute ProtocolDto request, Model model) {
        ProtocolDto response = protocolService.create(request);
        log.info("CREATE one protocol: {}", response);
        return "redirect:/api/protocols/main";
    }

    @GetMapping("/protocols/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        ProtocolDto protocol = protocolService.findOne(id);
        getSelectionLists(model, protocol);
        return "/protocols/protocols-edit";
    }

    private void getSelectionLists(Model model, ProtocolDto protocol) {
        List<AutomobileDto> automobileDtoList = automobileService.list();
        List<ViolationDto> violationDtoList = violationService.list();
        List<DriverDto> driverDtoList = driverService.list();
        List<PolicemanDto> policemanDtoList = policemanService.list();

        model.addAttribute("automobileList", automobileDtoList);
        model.addAttribute("violationList", violationDtoList);
        model.addAttribute("driverList", driverDtoList);
        model.addAttribute("policemanList", policemanDtoList);
        model.addAttribute("protocol", protocol);
    }

    @PostMapping("/protocols/edit")
    public String update(@ModelAttribute ProtocolDto request, Model model) {
        protocolService.update(request);
        log.info("UPDATE one protocol");
        return "redirect:/api/protocols/main";
    }

    @GetMapping("/protocols/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        ProtocolDto protocolDto = protocolService.findOne(id);
        protocolService.delete(protocolDto);
        log.info("DELETE one protocol");
        return "redirect:/api/protocols/main";
    }

    @GetMapping("/protocols/byPrepDate")
    public List<ProtocolDto> prepDateSortedList() {
        List<ProtocolDto> list = protocolService.prepDateSortedList();
        log.info("GET all protocols asc (preparation date): {}", list);
        return list;
    }

    @GetMapping("/protocols/find")
    public List<ProtocolDto> findByCriteria(@RequestParam(required = false)
                                                    String violationName,
                                            @RequestParam(required = false)
                                                    String isActive,
                                            @RequestParam(required = false)
                                                    String isNotActive,
                                            @RequestParam(required = false)
                                                    Long dueDate,
                                            @RequestParam(required = false)
                                                    String name) {
        List<ProtocolDto> response = protocolService.findByCriteria(violationName, isActive, isNotActive, dueDate, name);
        log.info("GET all protocols by criteria: {}", response);
        return response;
    }

    @GetMapping("/protocols/labQueries/1")
    public List<ProtocolDto> selectByCurrentDayAndNamesakePoliceman(@RequestParam String surname) {
        List<ProtocolDto> response = protocolService.labQuery3_7(surname.toLowerCase());
        log.info("LAB QUERY 3.7");
        return response;
    }
}
