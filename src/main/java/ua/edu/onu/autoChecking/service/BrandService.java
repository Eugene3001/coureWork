package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Brand;
import ua.edu.onu.autoChecking.dao.repositories.BrandRepository;
import ua.edu.onu.autoChecking.dto.BrandDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

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

    private final Function<Brand, BrandDto> brandToDto = entity -> BrandDto.builder()
            .brandName(entity.getBrandName())
            .id(entity.getBrandId())
            .build();

    private final Function<BrandDto, Brand> dtoToBrand = dto -> Brand.builder()
            .brandName(dto.getBrandName())
            .brandId(dto.getId())
            .build();

    public List<BrandDto> list() {
        List<BrandDto> response = new LinkedList<>();
        brandRepository.findAll().forEach(brand -> response.add(brandToDto.apply(brand)));
        return response;
    }

    public BrandDto findOne(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(NotFoundException::new);
        return brandToDto.apply(brand);
    }

    public BrandDto create(BrandDto brandDto) {
        return brandToDto.apply(brandRepository.save(dtoToBrand.apply(brandDto)));
    }

    public void update(BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Brand.class));

        brand.setBrandName(brandDto.getBrandName());

        brandRepository.save(brand);
    }

    public void delete(BrandDto brandDto) {
        brandRepository.findById(brandDto.getId()).orElseThrow(() -> NotFoundException.notFoundWhenDelete(Brand.class));
        brandRepository.deleteById(brandDto.getId());
    }
}
