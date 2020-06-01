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
import ua.edu.onu.autoChecking.dto.BrandDto;
import ua.edu.onu.autoChecking.service.BrandService;

import java.util.List;

@Controller
@Slf4j
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/main")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String list(Model model) {
        List<BrandDto> list = brandService.list();
        log.info("GET all brands: {}", list);

        model.addAttribute("list", list);
        return "brands/brands";
    }

    @GetMapping("/brands/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String createPage(Model model) {
        return "brands/brands-create";
    }

    @PostMapping("/brands/create")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String create(@ModelAttribute BrandDto request, Model model) {
        BrandDto response = brandService.create(request);
        log.info("CREATE one brand: {}", response);
        return "redirect:/brands/main";
    }

    @GetMapping("/brands/delete/{id}")
    @PreAuthorize("hasAuthority('app_admin')")
    public String delete(@PathVariable("id")Long id, Model model) {
        BrandDto brandDto = brandService.findOne(id);
        brandService.delete(brandDto);
        log.info("DELETE one brand");
        return "redirect:/brands/main";
    }

    @GetMapping("/brands/edit/{id}")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String editPage(Model model, @PathVariable("id") Long id) {
        BrandDto brand = brandService.findOne(id);

        model.addAttribute("brand", brand);
        return "/brands/brands-edit";
    }

    @PostMapping("/brands/edit")
    @PreAuthorize("hasAnyAuthority('app_admin', 'app_user', 'app_policeman')")
    public String update(@ModelAttribute BrandDto request, Model model) {
        brandService.update(request);
        log.info("UPDATE one brand");
        return "redirect:/brands/main";
    }
}
