package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
}
