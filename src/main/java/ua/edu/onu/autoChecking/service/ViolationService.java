package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Violation;
import ua.edu.onu.autoChecking.dao.repositories.ViolationRepository;
import ua.edu.onu.autoChecking.dao.repositories.spec.ViolationSpec;
import ua.edu.onu.autoChecking.dto.ViolationDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ViolationService {
    private ViolationRepository violationRepository;

    @Autowired
    public ViolationService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    private final Function<Violation, ViolationDto> violationToDto = entity -> ViolationDto.builder()
            .violationNumber(entity.getId())
            .court(entity.getCourt())
            .moneyPenalty(entity.getMoneyPenalty())
            .violation(entity.getViolation())
            .build();

    private final Function<ViolationDto, Violation> dtoToViolation = dto -> Violation.builder()
            .id(violationRepository.getViolationNumberByViolation(dto.getViolation()))
            .court(dto.getCourt())
            .moneyPenalty(dto.getMoneyPenalty())
            .violation(dto.getViolation())
            .build();

    public List<ViolationDto> list() {
        List<ViolationDto> response = new LinkedList<>();
        violationRepository.findAll().forEach(violation -> response.add(violationToDto.apply(violation)));
        return response;
    }

    public ViolationDto findOne(Long id) {
        Violation violation = violationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return violationToDto.apply(violation);
    }

    public ViolationDto create(ViolationDto violationDto) {
        return violationToDto.apply(violationRepository.save(dtoToViolation.apply(violationDto)));
    }

    public void update(ViolationDto violationDto) {
        Violation violation = violationRepository.findByViolation(violationDto.getViolation())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Violation.class));

        violation.setCourt(violationDto.getCourt());
        violation.setMoneyPenalty(violationDto.getMoneyPenalty());

        violationRepository.save(violation);
    }

    public void delete(ViolationDto violationDto) {
        Violation violation = violationRepository.findByViolation(violationDto.getViolation())
                .orElseThrow(() -> NotFoundException.notFoundWhenDelete(Violation.class));

        violationRepository.delete(violation);
    }

    public List<ViolationDto> findByCriteria(Float first, Float second, String isCourt, String isNotCourt) {
        List<ViolationDto> response = new LinkedList<>();

        if (isCourt.equals("")) {
            isCourt = null;
        }
        if (isNotCourt.equals("")) {
            isNotCourt = null;
        }

        violationRepository.findAll(ViolationSpec.buildSearchSpec(first, second, isCourt, isNotCourt))
                .forEach(violation -> response.add(violationToDto.apply(violation)));

        return response;
    }

    public List<ViolationDto> labQuery3_6(Long count) {
        List<ViolationDto> response = new LinkedList<>();

        violationRepository.selectSeveralMostPopularViolations(count)
                .forEach(violation -> response.add(violationToDto.apply(violation)));

        return response;
    }
}
