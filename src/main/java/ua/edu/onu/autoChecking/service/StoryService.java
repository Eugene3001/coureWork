package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;
import ua.edu.onu.autoChecking.dao.repositories.StoryRepository;
import ua.edu.onu.autoChecking.dto.StoryDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class StoryService {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    private final Function<Story, StoryDto> storyToDto = entity -> StoryDto.builder()
            .autoId(entity.getId().getAutoId())
            .driverId(entity.getId().getDriverId())
            .finishDate(entity.getFinishDate())
            .startDate(entity.getId().getStartDate())
            .userPassport(entity.getUserPassport())
            .build();

    private final Function<StoryDto, Story> dtoToStory = dto -> Story.builder()
            .id(new StoryId(dto.getAutoId(), dto.getDriverId(), dto.getStartDate()))
            .finishDate(dto.getFinishDate())
            .userPassport(dto.getUserPassport())
            .build();

    public List<StoryDto> list() {
        List<StoryDto> response = new LinkedList<>();
        storyRepository.findAll().forEach(story -> response.add(storyToDto.apply(story)));
        return response;
    }

    public StoryDto create(StoryDto storyDto) {
        return storyToDto.apply(storyRepository.save(dtoToStory.apply(storyDto)));
    }

    public List<StoryDto> startDateSortedList() {
        List<StoryDto> response = new LinkedList<>();
        storyRepository.getStartDateSortedList().forEach(story -> response.add(storyToDto.apply(story)));
        return response;
    }

    public List<StoryDto> finishDateSortedList() {
        List<StoryDto> response = new LinkedList<>();
        storyRepository.getFinishDateSortedList().forEach(story -> response.add(storyToDto.apply(story)));
        return response;
    }
}
