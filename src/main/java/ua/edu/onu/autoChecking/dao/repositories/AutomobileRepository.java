package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Automobile;

public interface AutomobileRepository extends CrudRepository<Automobile, Long> {
}
