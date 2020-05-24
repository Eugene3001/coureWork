package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, StoryId>, JpaSpecificationExecutor<Story> {
    //Sorting
    @Query("select s from Story s order by s.id.startDate asc")
    List<Story> getStartDateSortedList();

    @Query("select s from Story s order by s.finishDate desc")
    List<Story> getFinishDateSortedList();

    //LabQueries
    @Query(value =
            "select \"S\".auto_id, \"S\".driver_id, \"S\".user_passport, \"S\".start_date, \"S\".finish_date " +
            "\t   from Story \"S\" " +
            "join Automobile \"A\" using(auto_id)\n" +
            "join Driver \"D\" using(driver_id)\n" +
            "join Driver \"DD\" on \"DD\".passport = \"S\".user_passport\n" +
            "\n" +
            "where \"A\".registration_number = ?1 and\n" +
            "\t  extract(year from \"S\".start_date) > ?2 and \n" +
            "\t  extract(year from \"S\".start_date) < ?3",
            nativeQuery = true
    )
    List<Story> selectByPeriodAndRegistrationNumber(String registrationNumber, Long beginYear, Long endYear);
}
