package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Model;
import ua.edu.onu.autoChecking.dao.repositories.ModelRepository;
import ua.edu.onu.autoChecking.dto.ModelDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    private final Function<Model, ModelDto> modelToDto = entity -> ModelDto.builder()
            .bodyType(entity.getBodyType())
            .manufYear(entity.getManufYear())
            .brandId(entity.getBrandId())
            .modelId(entity.getModelId())
            .modelName(entity.getModelName())
            .build();

    private final Function<ModelDto, Model> dtoToModel = dto -> Model.builder()
            .bodyType(dto.getBodyType())
            .brandId(dto.getBrandId())
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
}
