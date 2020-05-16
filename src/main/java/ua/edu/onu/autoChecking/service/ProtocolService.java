package ua.edu.onu.autoChecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.onu.autoChecking.dao.entities.Protocol;
import ua.edu.onu.autoChecking.dao.repositories.ProtocolRepository;
import ua.edu.onu.autoChecking.dto.ProtocolDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class ProtocolService {
    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolService(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    private final Function<Protocol, ProtocolDto> protocolToDto = entity -> ProtocolDto.builder()
            .autoId(entity.getAutoId())
            .driverId(entity.getDriverId())
            .dueDate(entity.getDueDate())
            .id(entity.getId())
            .prepDate(entity.getPrepDate())
            .status(entity.getStatus())
            .tokenNumber(entity.getTokenNumber())
            .violationNumber(entity.getViolationNumber())
            .build();

    private final Function<ProtocolDto, Protocol> dtoToProtocol = dto -> Protocol.builder()
            .autoId(dto.getAutoId())
            .driverId(dto.getDriverId())
            .dueDate(dto.getDueDate())
            .id(dto.getId())
            .prepDate(dto.getPrepDate())
            .status(dto.getStatus())
            .tokenNumber(dto.getTokenNumber())
            .violationNumber(dto.getViolationNumber())
            .build();

    public List<ProtocolDto> list() {
        List<ProtocolDto> response = new LinkedList<>();
        protocolRepository.findAll().forEach(protocol -> response.add(protocolToDto.apply(protocol)));
        return response;
    }

    public ProtocolDto create(ProtocolDto protocolDto) {
        return protocolToDto.apply(protocolRepository.save(dtoToProtocol.apply(protocolDto)));
    }
}
