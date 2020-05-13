package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Policeman;

public interface PolicemanRepository extends JpaRepository<Policeman, Long> {
}
