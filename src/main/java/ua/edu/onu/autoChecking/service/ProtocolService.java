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
import ua.edu.onu.autoChecking.dao.repositories.spec.ProtocolSpec;
import ua.edu.onu.autoChecking.dto.ProtocolDto;
import ua.edu.onu.autoChecking.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ProtocolService {
    private ProtocolRepository protocolRepository;
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
            .vehicleIdNumber(automobileRepository.getVehicleIdNumberByAutoId(entity.getId()))
            .driverPassport(driverRepository.getPassportByDriverId(entity.getId()))
            .dueDate(entity.getDueDate())
            .id(entity.getId())
            .prepDate(entity.getPrepDate())
            .status(entity.getStatus())
            .tokenNumber(entity.getPoliceman().getId())
            .violationName(violationRepository.getViolationByViolationNumber(entity.getViolation().getId()))
            .build();

    private final Function<ProtocolDto, Protocol> dtoToProtocol = dto -> Protocol.builder()
            .automobile(automobileRepository.findByVehicleIdNumber(dto.getVehicleIdNumber()).orElseThrow(NotFoundException::new))
            .driver(driverRepository.findByPassport(dto.getDriverPassport()).orElseThrow(NotFoundException::new))
            .dueDate(dto.getDueDate())
            .id(dto.getId())
            .prepDate(dto.getPrepDate())
            .status(dto.getStatus())
            .policeman(policemanRepository.findById(dto.getTokenNumber())
                    .orElseThrow(() -> NotFoundException.returnNotFoundEntity(dto, Policeman.class.getName())))
            .violation(violationRepository.findByViolation(dto.getViolationName()).orElseThrow(NotFoundException::new))
            .build();

    public List<ProtocolDto> list() {
        List<ProtocolDto> response = new LinkedList<>();
        protocolRepository.findAll().forEach(protocol -> response.add(protocolToDto.apply(protocol)));
        return response;
    }

    public ProtocolDto create(ProtocolDto protocolDto) {
        return protocolToDto.apply(protocolRepository.save(dtoToProtocol.apply(protocolDto)));
    }

    public void delete(ProtocolDto protocolDto) {
        Protocol protocol = protocolRepository.findById(protocolDto.getId())
                .orElseThrow(() -> NotFoundException.notFoundWhenDelete(Protocol.class));

        protocolRepository.delete(protocol);
    }

    public void update(ProtocolDto protocolDto) {
        Protocol protocol = protocolRepository.findById(protocolDto.getId())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Protocol.class));

        protocol.setAutomobile(automobileRepository.findByVehicleIdNumber(protocolDto.getVehicleIdNumber())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Protocol.class)));

        protocol.setDriver(driverRepository.findByPassport(protocolDto.getDriverPassport())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Protocol.class)));

        protocol.setDueDate(protocolDto.getDueDate());
        protocol.setId(protocolDto.getId());
        protocol.setPoliceman(policemanRepository.findById(protocolDto.getTokenNumber())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Protocol.class)));

        protocol.setPrepDate(protocolDto.getPrepDate());
        protocol.setStatus(protocolDto.getStatus());
        protocol.setViolation(violationRepository.findByViolation(protocolDto.getViolationName())
                .orElseThrow(() -> NotFoundException.notFoundWhenUpdate(Protocol.class)));

        protocolRepository.save(protocol);
    }

    public List<ProtocolDto> prepDateSortedList() {
        List<ProtocolDto> response = new LinkedList<>();
        protocolRepository.getPrepDateSortedList().forEach(protocol -> response.add(protocolToDto.apply(protocol)));
        return response;
    }

    public List<ProtocolDto> findByCriteria(String violationName, String isActive, String isNotActive, Long dueDate, String name) {
        List<ProtocolDto> response = new LinkedList<>();
        Violation violation = violationRepository.findByViolation(violationName).orElse(null);
        Policeman policeman = policemanRepository.findByName(name).orElse(null);

        protocolRepository.findAll(ProtocolSpec.buildSearchSpec(violation, isActive, isNotActive, dueDate, policeman))
                .forEach(protocol -> response.add(protocolToDto.apply(protocol)));

        return response;
    }
}
