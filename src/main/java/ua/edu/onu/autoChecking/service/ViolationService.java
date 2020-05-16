package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Violation;
import ua.edu.onu.autoChecking.dao.repositories.ViolationRepository;
import ua.edu.onu.autoChecking.dto.ViolationDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ViolationService {
    private final ViolationRepository violationRepository;

    @Autowired
    public ViolationService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    private final Function<Violation, ViolationDto> violationToDto = entity -> ViolationDto.builder()
            .court(entity.getCourt())
            .id(entity.getId())
            .moneyPenalty(entity.getMoneyPenalty())
            .violation(entity.getViolation())
            .build();

    private final Function<ViolationDto, Violation> dtoToViolation = dto -> Violation.builder()
            .court(dto.getCourt())
            .id(dto.getId())
            .moneyPenalty(dto.getMoneyPenalty())
            .violation(dto.getViolation())
            .build();

    public List<ViolationDto> list() {
        List<ViolationDto> response = new LinkedList<>();
        violationRepository.findAll().forEach(violation -> response.add(violationToDto.apply(violation)));
        return response;
    }

    public ViolationDto create(ViolationDto violationDto) {
        ViolationDto response = violationToDto.apply(violationRepository.save(dtoToViolation.apply(violationDto)));
        return response;
    }
}
