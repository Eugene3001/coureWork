package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Color;

import java.util.Date;
import java.util.List;

public interface AutomobileRepository extends JpaRepository<Automobile, Long>, JpaSpecificationExecutor<Automobile> {
    //Sorting
    @Query("select a from Automobile a order by a.registrationDate asc")
    List<Automobile> getDateSortedListAsc();

    //Selection
//    List<Automobile> findAllByColorAndRegistrationDateBetweenOrColorOrRegistrationDateBetween(Color color, Date begin, Date end, Color color2, Date begin2, Date end2);

    @Query("select a from Automobile a where (:color is not null and :begin is null and :finish is null and a.color.colorId = :color)")
    List<Automobile> findByCriteria(@Param("color")Long color, @Param("begin")Date begin, @Param("finish")Date finish);
}

