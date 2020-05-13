package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
