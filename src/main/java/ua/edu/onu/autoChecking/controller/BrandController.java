package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dto.BrandDto;
import ua.edu.onu.autoChecking.service.BrandService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public List<BrandDto> list() {
        List<BrandDto> response = brandService.list();
        log.info("GET all brands: {}", response);
        return response;
    }

    @PostMapping("/brands")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public BrandDto create(@RequestBody BrandDto request) {
        BrandDto response = brandService.create(request);
        log.info("CREATE one brand: {}", response);
        return response;
    }
}
