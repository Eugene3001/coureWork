package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.DriverDto;
import ua.edu.onu.autoChecking.dto.dtoSpec.DriverDtoSpec;
import ua.edu.onu.autoChecking.service.DriverService;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers/main")
    public String list(Model model) {
        List<DriverDto> list = driverService.list();
        log.info("GET all drivers: {}", list);

        model.addAttribute("list", list);

        return "drivers/drivers";
    }

    @GetMapping("/drivers/create")
    public String createPage(Model model) {
        return "drivers/drivers-create";
    }

    @PostMapping("/drivers/create")
    public String create(@ModelAttribute DriverDto request, Model model) {
        DriverDto response = driverService.create(request);
        log.info("CREATE one driver: {}", response);
        return "redirect:/api/drivers/main";
    }

    @PutMapping("/drivers")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody DriverDto request) {
        driverService.update(request);
        log.info("UPDATE one driver");
    }

    @DeleteMapping("/drivers")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody DriverDto request) {
        driverService.delete(request);
        log.info("DELETE one driver");
    }

    @GetMapping("/drivers/byBirthDate")
    public List<DriverDto> birthDateSortedList() {
        List<DriverDto> list = driverService.birthDateSortedList();
        log.info("GET all drivers asc (birth date): {}", list);
        return list;
    }

    @GetMapping("/drivers/find")
    public List<DriverDto> findByCriteria(@RequestParam(required = false)
                                          @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                  Date begin,
                                          @RequestParam(required = false)
                                          @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                  Date end,
                                          @RequestParam(required = false)
                                                  String city,
                                          @RequestParam(required = false)
                                                  String street,
                                          @RequestParam(required = false)
                                                  String house,
                                          @RequestParam(required = false)
                                                  Long flat,
                                          @RequestParam(required = false)
                                                  String name,
                                          @RequestParam(required = false)
                                                  String surname,
                                          @RequestParam(required = false)
                                                  String patronymic) {
        List<DriverDto> response = driverService.findByCriteria(begin, end, city,
                                                                street, house, flat,
                                                                name, surname, patronymic);
        log.info("GET all cars by criteria: {}", response);
        return response;
    }

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
