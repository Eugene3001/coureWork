package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.edu.onu.autoChecking.dao.entities.Color;

import java.util.Optional;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
    Optional<Color> findByColorName(String colorName);
}
