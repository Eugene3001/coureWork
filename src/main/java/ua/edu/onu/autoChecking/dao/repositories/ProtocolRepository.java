package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Protocol;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {
}
