package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Violation;

public interface ViolationRepository extends CrudRepository<Violation, Long> {
}
