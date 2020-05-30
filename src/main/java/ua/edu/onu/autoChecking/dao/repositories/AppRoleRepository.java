package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.AppRole;

import java.util.List;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
    @Query("select ur.appRole.roleName from UserRole ur where ur.appUser.userId = :userId")
    List<String> getRoleNames(Long userId);
}
