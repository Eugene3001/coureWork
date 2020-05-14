package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {
}
