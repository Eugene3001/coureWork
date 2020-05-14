package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Protocol;

public interface ProtocolRepository extends CrudRepository<Protocol, Long> {
}
