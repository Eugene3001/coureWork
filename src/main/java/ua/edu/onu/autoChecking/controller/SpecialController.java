package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.DriverDtoSpec;
import ua.edu.onu.autoChecking.service.AutomobileService;
import ua.edu.onu.autoChecking.service.DriverService;
import ua.edu.onu.autoChecking.service.PolicemanService;

import java.util.LinkedList;
import java.util.List;

@Controller
@Slf4j
public class SpecialController {
    private AutomobileService automobileService;
    private DriverService driverService;
    private PolicemanService policemanService;

    @Autowired
    public SpecialController(AutomobileService automobileService, DriverService driverService, PolicemanService policemanService) {
        this.automobileService = automobileService;
        this.driverService = driverService;
        this.policemanService = policemanService;
    }

    @GetMapping("/specials/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String main(Model model) {
        List<String[]> queries = new LinkedList<>();

        String labQuery4_3 = "Vehicles that have never participated in violations";
        String link4_3 = "/specials/4_3";
        queries.add(new String[] {
                labQuery4_3, link4_3
        });

        String labQuery3_5 = "Drivers with late payments";
        String link3_5 = "/specials/3_5";
        queries.add(new String[] {
                labQuery3_5,
                link3_5
        });

        String labQuery4_2 = "Drivers who violated traffic rules more than three times this year";
        String link4_2 = "/specials/4_2";
        queries.add(new String[] {
                labQuery4_2,
                link4_2
        });

        model.addAttribute("queries", queries);
        return "specials/specials-main";
    }

    @GetMapping("/specials/4_3")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String labQuery4_3(Model model) {
        List<AutomobileDto> response = automobileService.labQuery4_3();

        model.addAttribute("list", response);
        model.addAttribute("title", "Vehicles that have never participated in violations");
        return "autos/autos";
    }

    @GetMapping("/specials/3_5")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String labQuery3_5(Model model) {
        List<DriverDto> response = driverService.labQuery3_5();

        model.addAttribute("list", response);
        model.addAttribute("title", "Drivers with late payments");
        return "drivers/drivers";
    }

    @GetMapping("/specials/4_2")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_policeman')")
    public String labQuery4_2(Model model) {
        List<DriverDtoSpec> response = driverService.labQuery4_2();

        model.addAttribute("list", response);
        model.addAttribute("title", "Drivers who violated traffic rules more than three times this year");
        return "specials/driver-spec";
    }
}
