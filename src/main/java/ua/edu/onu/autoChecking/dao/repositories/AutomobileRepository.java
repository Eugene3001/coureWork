package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Automobile;

public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
}
