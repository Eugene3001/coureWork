package ua.edu.onu.autoChecking.dao.repositories;

import org.hibernate.annotations.LazyToOne;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.edu.onu.autoChecking.dao.entities.Violation;

import java.util.Optional;

public interface ViolationRepository extends CrudRepository<Violation, Long>, JpaSpecificationExecutor<Violation> {
    Optional<Violation> findByViolation(String violationName);

    @Query("select v.violation from Violation v where v.id = :violationNumber")
    String getViolationByViolationNumber(@Param("violationNumber")Long violationNumber);

    @Query("select v.id from Violation v where v.violation = :violation")
    Long getViolationNumberByViolation(@Param("violation")String violation);
}
