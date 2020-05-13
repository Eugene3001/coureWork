package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Violation;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
}
