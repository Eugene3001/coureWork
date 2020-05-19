package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Policeman;

import java.util.Optional;

public interface PolicemanRepository extends CrudRepository<Policeman, Long>, JpaSpecificationExecutor<Policeman> {
    Optional<Policeman> findByName(String name);
}
