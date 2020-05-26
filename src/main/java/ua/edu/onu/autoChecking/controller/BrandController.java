package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.AutomobileDto;
import ua.edu.onu.autoChecking.dto.BrandDto;
import ua.edu.onu.autoChecking.service.BrandService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/main")
    public String list(Model model) {
        List<BrandDto> list = brandService.list();
        log.info("GET all brands: {}", list);

        model.addAttribute("list", list);
        return "brands";
    }

    @GetMapping("/brands/create")
    public String createPage(Model model) {
        return "brands-create";
    }

    @PostMapping("/brands/create")
    public String create(@ModelAttribute BrandDto request, Model model) {
        BrandDto response = brandService.create(request);
        log.info("CREATE one brand: {}", response);
        return "redirect:/api/brands/main";
    }

    @DeleteMapping("/brands")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody BrandDto request) {
        brandService.delete(request);
        log.info("DELETE one brand");
    }

    @PutMapping("/brands")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody BrandDto request) {
        brandService.update(request);
        log.info("UPDATE one brand");
    }
}
