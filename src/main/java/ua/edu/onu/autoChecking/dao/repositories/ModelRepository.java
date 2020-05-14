package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Model;

public interface ModelRepository extends CrudRepository<Model, Long> {
}
