package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Driver;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    //Sorting
    @Query("select d from Driver d order by d.birthDate asc")
    List<Driver> getBirthDateSortedList();

    //Selection
}
