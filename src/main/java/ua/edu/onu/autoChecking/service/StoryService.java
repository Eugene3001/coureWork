package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;
import ua.edu.onu.autoChecking.dao.repositories.DriverRepository;
import ua.edu.onu.autoChecking.dao.repositories.StoryRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.StorySpec;
import ua.edu.onu.autoChecking.dto.StoryDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.text.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class StoryService {
    private StoryRepository storyRepository;
    private AutomobileRepository automobileRepository;
    private DriverRepository driverRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository, AutomobileRepository automobileRepository,
                        DriverRepository driverRepository) {
        this.storyRepository = storyRepository;
        this.automobileRepository = automobileRepository;
        this.driverRepository = driverRepository;
    }

    private final Function<Story, StoryDto> storyToDto = entity -> StoryDto.builder()
            .vehicleIdNumber(automobileRepository.findAutomobileByAutoId((entity.getAutomobile().getAutoId())).orElseThrow(NotFoundException::new).getVehicleIdNumber())
            .driverPassport(driverRepository.getPassportByDriverId(entity.getDriver().getDriverId()))
            .finishDate(entity.getFinishDate())
            .startDate(entity.getId().getStartDate())
            .userPassport(entity.getUserPassport())
            .autoId(entity.getId().getAutoId())
            .driverId(entity.getId().getDriverId())
            .build();

    private final Function<StoryDto, Story> dtoToStory = dto -> {
        Automobile automobile = automobileRepository.findByVehicleIdNumber(dto.getVehicleIdNumber())
                .orElseThrow(NotFoundException::new);

        Driver driver = driverRepository.findByPassport(dto.getDriverPassport())
                .orElseThrow(NotFoundException::new);

        return Story.builder()
                .id(new StoryId(automobile.getAutoId(), driver.getDriverId(), dto.getStartDate()))
                .automobile(automobile)
                .driver(driver)
                .finishDate(dto.getFinishDate())
                .userPassport(dto.getUserPassport())
                .build();
    };

    public List<StoryDto> list() {
        List<StoryDto> response = new LinkedList<>();
        storyRepository.findAll().forEach(story -> response.add(storyToDto.apply(story)));
        return response;
    }

    public StoryDto findOne(StoryId id) {
        Story story = storyRepository.findById(id).orElseThrow(NotFoundException::new);
        return storyToDto.apply(story);
    }

    public StoryDto create(StoryDto storyDto) {
        return storyToDto.apply(storyRepository.save(dtoToStory.apply(storyDto)));
    }

    public void update(StoryDto storyDto) {
        Story story = storyRepository
                .findById(new StoryId(storyDto.getAutoId(), storyDto.getDriverId(), storyDto.getStartDate()))
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Story.class));

        story.setUserPassport(storyDto.getUserPassport());
        story.setFinishDate(storyDto.getFinishDate());

        storyRepository.save(story);
    }

    public void delete(StoryDto storyDto) {
        Story story = dtoToStory.apply(storyDto);
        storyRepository.findById(story.getId()).orElseThrow(() -> NotFoundException.notFoundWhenDelete(Story.class));
        storyRepository.deleteById(story.getId());
    }

//    public List<StoryDto> startDateSortedList() {
//        List<StoryDto> response = new LinkedList<>();
//        storyRepository.getStartDateSortedList().forEach(story -> response.add(storyToDto.apply(story)));
//        return response;
//    }

//    public List<StoryDto> finishDateSortedList() {
//        List<StoryDto> response = new LinkedList<>();
//        storyRepository.getFinishDateSortedList().forEach(story -> response.add(storyToDto.apply(story)));
//        return response;
//    }

    public List<StoryDto> findByCriteria(String userPassport, String isOwner, String isNotOwner) {
        List<StoryDto> response = new LinkedList<>();

        storyRepository.findAll(StorySpec.buildSearchSpec(userPassport, isOwner, isNotOwner))
                .forEach(story -> response.add(storyToDto.apply(story)));

        return response;
    }

//    public List<StoryDto> labQuery3_2(String registrationNumber, Long beginYear, Long endYear) {
//        List<StoryDto> response = new LinkedList<>();
//
//        storyRepository.selectByPeriodAndRegistrationNumber(registrationNumber, beginYear, endYear)
//                .forEach(story -> response.add(storyToDto.apply(story)));
//
//        return response;
//    }
}
