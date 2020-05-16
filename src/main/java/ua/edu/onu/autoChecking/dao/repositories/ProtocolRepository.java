package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Protocol;

import java.util.List;

public interface ProtocolRepository extends CrudRepository<Protocol, Long> {
    //Sorting
    @Query("select p from Protocol p order by p.prep_date asc")
    List<Protocol> getPrepDateSortedList();

    //Selection
}
