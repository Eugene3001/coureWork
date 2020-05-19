package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Violation;

import java.util.Optional;

public interface ViolationRepository extends CrudRepository<Violation, Long> {
    Optional<Violation> findByViolation(String violationName);
}
