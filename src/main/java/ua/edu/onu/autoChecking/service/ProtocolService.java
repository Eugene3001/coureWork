package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dao.entities.Protocol;
import ua.edu.onu.autoChecking.dao.entities.Violation;
import ua.edu.onu.autoChecking.dao.repositories.AutomobileRepository;
import ua.edu.onu.autoChecking.dao.repositories.DriverRepository;
import ua.edu.onu.autoChecking.dao.repositories.PolicemanRepository;
import ua.edu.onu.autoChecking.dao.repositories.ProtocolRepository;
import ua.edu.onu.autoChecking.dao.repositories.ViolationRepository;
import ua.edu.onu.autoChecking.dto.ProtocolDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ProtocolService {
    private final ProtocolRepository protocolRepository;
    private DriverRepository driverRepository;
    private PolicemanRepository policemanRepository;
    private ViolationRepository violationRepository;
    private AutomobileRepository automobileRepository;

    @Autowired
    public ProtocolService(ProtocolRepository protocolRepository, DriverRepository driverRepository,
                           PolicemanRepository policemanRepository, ViolationRepository violationRepository,
                           AutomobileRepository automobileRepository) {
        this.protocolRepository = protocolRepository;
        this.driverRepository = driverRepository;
        this.policemanRepository = policemanRepository;
        this.violationRepository = violationRepository;
        this.automobileRepository = automobileRepository;
    }

    private final Function<Protocol, ProtocolDto> protocolToDto = entity -> ProtocolDto.builder()
            .autoId(entity.getAutomobile().getAutoId())
            .driverId(entity.getDriver().getDriverId())
            .dueDate(entity.getDueDate())
            .id(entity.getId())
            .prepDate(entity.getPrepDate())
            .status(entity.getStatus())
            .tokenNumber(entity.getPoliceman().getId())
            .violationNumber(entity.getViolation().getId())
            .build();

    private final Function<ProtocolDto, Protocol> dtoToProtocol = dto -> Protocol.builder()
            .automobile(automobileRepository.findById(dto.getAutoId()).orElseThrow(
                    () -> NotFoundException.returnNotFoundEntity(dto, Automobile.class.getName()))
            )
            .driver(driverRepository.findById(dto.getDriverId()).orElseThrow(
                    () -> NotFoundException.returnNotFoundEntity(dto, Driver.class.getName()))
            )
            .dueDate(dto.getDueDate())
            .id(dto.getId())
            .prepDate(dto.getPrepDate())
            .status(dto.getStatus())
            .policeman(policemanRepository.findById(dto.getTokenNumber()).orElseThrow(
                    () -> NotFoundException.returnNotFoundEntity(dto, Policeman.class.getName()))
            )
            .violation(violationRepository.findById(dto.getViolationNumber()).orElseThrow(
                    () -> NotFoundException.returnNotFoundEntity(dto, Violation.class.getName()))
            )
            .build();

    public List<ProtocolDto> list() {
        List<ProtocolDto> response = new LinkedList<>();
        protocolRepository.findAll().forEach(protocol -> response.add(protocolToDto.apply(protocol)));
        return response;
    }

    public ProtocolDto create(ProtocolDto protocolDto) {
        return protocolToDto.apply(protocolRepository.save(dtoToProtocol.apply(protocolDto)));
    }

    public List<ProtocolDto> prepDateSortedList() {
        List<ProtocolDto> response = new LinkedList<>();
        protocolRepository.getPrepDateSortedList().forEach(protocol -> response.add(protocolToDto.apply(protocol)));
        return response;
    }
}
