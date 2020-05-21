package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Violation;

import java.util.Optional;

public interface ViolationRepository extends CrudRepository<Violation, Long>, JpaSpecificationExecutor<Violation> {
    Optional<Violation> findByViolation(String violationName);
}
