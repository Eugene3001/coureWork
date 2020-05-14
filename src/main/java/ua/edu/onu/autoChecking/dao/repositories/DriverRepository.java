package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
}
