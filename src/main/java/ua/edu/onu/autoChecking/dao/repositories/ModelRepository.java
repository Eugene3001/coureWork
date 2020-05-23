package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.edu.onu.autoChecking.dao.entities.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends CrudRepository<Model, Long>, JpaSpecificationExecutor<Model> {
    //Sorting
    @Query("select m from Model m order by m.manufYear asc")
    List<Model> getManufactureYearSortedList();

    //Selection
    Optional<Model> findByModelName(String modelName);

    @Query("select m.modelId from Model m where m.modelName = :modelName")
    Long getModelIdByModelName(@Param("modelName")String modelName);
}
