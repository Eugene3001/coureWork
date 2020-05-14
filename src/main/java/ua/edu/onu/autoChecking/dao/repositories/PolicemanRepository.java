package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Policeman;

public interface PolicemanRepository extends CrudRepository<Policeman, Long> {
}
