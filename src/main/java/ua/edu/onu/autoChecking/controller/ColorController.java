package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.ColorDto;
import ua.edu.onu.autoChecking.service.ColorService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors/main")
    public String list(Model model) {
        List<ColorDto> list = colorService.list();
        log.info("GET all colors: {}", list);

        model.addAttribute("list", list);

        return "/colors/colors";
    }

    @GetMapping("/colors/create")
    public String cratePage(Model model) {
        return "colors/colors-create";
    }

    @PostMapping("/colors/create")
    public String create(@ModelAttribute ColorDto request, Model model) {
        ColorDto response = colorService.create(request);
        log.info("CREATE one color: {}", response);
        return "redirect:/api/colors/main";
    }

    @GetMapping("/colors/edit/{colorId}")
    public String editPage(Model model, @PathVariable("colorId") Long colorId) {
        ColorDto color = colorService.findOne(colorId);

        model.addAttribute("color", color);
        return "/colors/colors-edit";
    }

    @PostMapping("/colors/edit")
    public String update(@ModelAttribute ColorDto request, Model model) {
        colorService.update(request);
        log.info("UPDATE one color");
        return "redirect:/api/colors/main";
    }

    @DeleteMapping("/colors")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody ColorDto request) {
        colorService.delete(request);
        log.info("DELETE one color");
    }
}
