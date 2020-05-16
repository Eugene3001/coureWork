package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Automobile;

import java.util.List;

public interface AutomobileRepository extends CrudRepository<Automobile, Long> {
    //Sorting
    @Query("select a from Automobile a order by a.registration_date asc")
    List<Automobile> getDateSortedListAsc();

    //Selection
}
