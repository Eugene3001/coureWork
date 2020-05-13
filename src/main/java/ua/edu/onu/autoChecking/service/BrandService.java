package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Brand;
import ua.edu.onu.autoChecking.dao.repositories.BrandRepository;
import ua.edu.onu.autoChecking.dto.BrandDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    private final Function<Brand, BrandDto> brandToDto = entity -> {
        return BrandDto.builder()
                .brandName(entity.getBrandName())
                .id(entity.getBrandId())
                .build();
    };

    public List<BrandDto> list() {
        List<BrandDto> response = new LinkedList<>();
        brandRepository.findAll().forEach(brand -> response.add(brandToDto.apply(brand)));
        return response;
    }
}
