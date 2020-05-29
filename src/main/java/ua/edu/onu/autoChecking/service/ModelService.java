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
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private final Function<Model, ModelDto> modelToDto = entity -> ModelDto.builder()
            .modelId(entity.getModelId())
            .bodyType(entity.getBodyType())
            .manufYear(entity.getManufYear())
            .modelName(entity.getModelName())
            .brandName(entity.getBrand().getBrandName())
            .build();

    private final Function<ModelDto, Model> dtoToModel = dto -> Model.builder()
            .bodyType(dto.getBodyType())
            .manufYear(dto.getManufYear())
            .modelName(dto.getModelName())
            .brand(brandRepository.findByBrandName(dto.getBrandName())
                    .orElseThrow(NotFoundException::new))
            .modelId(modelRepository.getModelIdByModelName(dto.getModelName()))
            .build();

    public List<ModelDto> list() {
        List<ModelDto> response = new LinkedList<>();
        modelRepository.findAll().forEach(model -> response.add(modelToDto.apply(model)));
        return response;
    }

    public ModelDto create(ModelDto modelDto) {
        Model model = modelRepository.save(dtoToModel.apply(modelDto));
        return modelToDto.apply(model);
    }

    public void delete(ModelDto modelDto) {
        Model model = modelRepository.findByModelName(modelDto.getModelName())
                .orElseThrow(() -> NotFoundException.notFoundWhenDelete(Model.class));

        modelRepository.delete(model);
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
