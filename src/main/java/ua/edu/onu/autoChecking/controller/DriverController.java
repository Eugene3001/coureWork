package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.DriverDtoSpec;
import ua.edu.onu.autoChecking.service.DriverService;

import java.util.List;

@Controller
@Slf4j
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String list(Model model) {
        List<DriverDto> list = driverService.list();
        log.info("GET all drivers: {}", list);

        model.addAttribute("list", list);

        return "drivers/drivers";
    }

    @GetMapping("/drivers/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String createPage(Model model) {
        return "drivers/drivers-create";
    }

    @PostMapping("/drivers/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String create(@ModelAttribute DriverDto request, Model model) {
        DriverDto response = driverService.create(request);
        log.info("CREATE one driver: {}", response);
        return "redirect:/drivers/main";
    }

    @GetMapping("/drivers/edit/{driverId}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String editPage(Model model, @PathVariable("driverId") Long driverId) {
        DriverDto driver = driverService.findOne(driverId);

        model.addAttribute("driver", driver);
        return "/drivers/drivers-edit";
    }

    @PostMapping("/drivers/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String update(@ModelAttribute DriverDto request, Model model) {
        driverService.update(request);
        log.info("UPDATE one driver");
        return "redirect:/drivers/main";
    }

    @GetMapping("/drivers/delete/{id}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("id") Long id, Model model) {
        DriverDto driverDto = driverService.findOne(id);
        driverService.delete(driverDto);
        log.info("DELETE one driver");
        return "redirect:/drivers/main";
    }

//    @GetMapping("/drivers/byBirthDate")
//    public List<DriverDto> birthDateSortedList() {
//        List<DriverDto> list = driverService.birthDateSortedList();
//        log.info("GET all drivers asc (birth date): {}", list);
//        return list;
//    }

//    @GetMapping("/drivers/find")
//    public String findByCriteria(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date begin,
//                                 @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,
//                                 @RequestParam String city,
//                                 @RequestParam String street,
//                                 @RequestParam String house,
//                                 @RequestParam Long flat,
//                                 @RequestParam String name,
//                                 @RequestParam String surname,
//                                 @RequestParam String patronymic,
//                                 Model model) {
//        List<DriverDto> response = driverService.findByCriteria(begin, end, city,
//                                                                street, house, flat,
//                                                                name, surname, patronymic);
//        log.info("GET all cars by criteria: {}", response);
//
//        model.addAttribute("list", response);
//        return "drivers/drivers";
//    }

    @GetMapping("/drivers/labQueries/1")
    public List<DriverDto> selectByPastDuePaymentDate() {
        List<DriverDto> response = driverService.labQuery3_5();
        log.info("LAB QUERY 3.5");
        return response;
    }

    @GetMapping("/drivers/labQueries/2")
    public List<DriverDtoSpec> firstView() {
        List<DriverDtoSpec> response = driverService.labQuery4_2();
        log.info("LAB QUERY 4.2");
        return response;
    }
}
