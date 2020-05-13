package ua.edu.onu.autoChecking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.onu.autoChecking.dao.entities.Brand;
import ua.edu.onu.autoChecking.dao.repositories.BrandRepository;
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
    public Brand create(@RequestBody Brand request) {
        Brand response = brandRepository.save(request);
        log.info("CREATE one brand: {}", response);
        return response;
    }

    @GetMapping("/brands/{ch}")
    @ResponseBody
    public Iterable<Brand> nativeTest(@PathVariable String ch) {
        Iterable<Brand> list = brandRepository.findBrandWhereFirstCharacterEqualA(ch + '%');
        log.info("GET CUSTOM LIST OF BRANDS {}", list);
        return list;
    }
}
