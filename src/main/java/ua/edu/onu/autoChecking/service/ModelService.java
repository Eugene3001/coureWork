package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Brand;
import ua.edu.onu.autoChecking.dao.entities.Model;
import ua.edu.onu.autoChecking.dao.repositories.BrandRepository;
import ua.edu.onu.autoChecking.dao.repositories.ModelRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.ModelSpec;
import ua.edu.onu.autoChecking.dto.ModelDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ModelService {
    private final ModelRepository modelRepository;
    private BrandRepository brandRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private final Function<Model, ModelDto> modelToDto = entity -> ModelDto.builder()
            .bodyType(entity.getBodyType())
            .manufYear(entity.getManufYear())
            .brandId(entity.getBrand().getBrandId())
            .modelId(entity.getModelId())
            .modelName(entity.getModelName())
            .build();

    private final Function<ModelDto, Model> dtoToModel = dto -> Model.builder()
            .bodyType(dto.getBodyType())
            .brand(brandRepository.findById(dto.getBrandId()).orElseThrow(
                    () -> NotFoundException.returnNotFoundEntity(dto, Brand.class.getName()))
            )
            .manufYear(dto.getManufYear())
            .modelId(dto.getModelId())
            .modelName(dto.getModelName())
            .build();

    public List<ModelDto> list() {
        List<ModelDto> response = new LinkedList<>();
        modelRepository.findAll().forEach(model -> response.add(modelToDto.apply(model)));
        return response;
    }

    public ModelDto create(ModelDto modelDto) {
        return modelToDto.apply(modelRepository.save(dtoToModel.apply(modelDto)));
    }

    public List<ModelDto> manufactureYearSortedList() {
        List<ModelDto> response = new LinkedList<>();
        modelRepository.getManufactureYearSortedList().forEach(model -> response.add(modelToDto.apply(model)));
        return response;
    }

    public List<ModelDto> findByCriteria(String bodyType, Date begin, Date end) {
        List<ModelDto> response = new LinkedList<>();
        modelRepository.findAll(ModelSpec.buildSearchSpec(bodyType, begin, end)).forEach(model -> response.add(modelToDto.apply(model)));
        return response;
    }
}
