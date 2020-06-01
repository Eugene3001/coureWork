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
import ua.edu.onu.autoChecking.dto.ColorDto;
import ua.edu.onu.autoChecking.service.ColorService;

import java.util.List;

@Controller
@Slf4j
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String list(Model model) {
        List<ColorDto> list = colorService.list();
        log.info("GET all colors: {}", list);

        model.addAttribute("list", list);

        return "/colors/colors";
    }

    @GetMapping("/colors/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String cratePage(Model model) {
        return "colors/colors-create";
    }

    @PostMapping("/colors/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String create(@ModelAttribute ColorDto request, Model model) {
        ColorDto response = colorService.create(request);
        log.info("CREATE one color: {}", response);
        return "redirect:/colors/main";
    }

    @GetMapping("/colors/edit/{colorId}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String editPage(Model model, @PathVariable("colorId") Long colorId) {
        ColorDto color = colorService.findOne(colorId);

        model.addAttribute("color", color);
        return "/colors/colors-edit";
    }

    @PostMapping("/colors/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String update(@ModelAttribute ColorDto request, Model model) {
        colorService.update(request);
        log.info("UPDATE one color");
        return "redirect:/colors/main";
    }

    @GetMapping("/colors/delete/{id}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("id")Long id, Model model) {
        ColorDto colorDto = colorService.findOne(id);
        colorService.delete(colorDto);
        log.info("DELETE one color");
        return "redirect:/colors/main";
    }
}
