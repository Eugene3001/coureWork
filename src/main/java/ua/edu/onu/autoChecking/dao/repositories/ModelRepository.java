package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
