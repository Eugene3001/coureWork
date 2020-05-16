package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Color;
import ua.edu.onu.autoChecking.dao.repositories.ColorRepository;
import ua.edu.onu.autoChecking.dto.ColorDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    private final Function<Color, ColorDto> colorToDto = entity -> ColorDto.builder()
            .colorName(entity.getColorName())
            .build();

    private final Function<ColorDto, Color> dtoToColor = dto -> Color.builder()
            .colorName(dto.getColorName())
            .build();

    public List<ColorDto> list() {
        List<ColorDto> response = new LinkedList<>();
        colorRepository.findAll().forEach(color -> response.add(colorToDto.apply(color)));
        return response;
    }

    public ColorDto create(ColorDto colorDto) {
        ColorDto response = colorToDto.apply(colorRepository.save(dtoToColor.apply(colorDto)));
        return response;
    }
}
