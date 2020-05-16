package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Model;

import java.util.List;

public interface ModelRepository extends CrudRepository<Model, Long> {
    //Sorting
    @Query("select m from Model m order by m.manufYear asc")
    List<Model> getManufactureYearSortedList();

    //Selection
}
