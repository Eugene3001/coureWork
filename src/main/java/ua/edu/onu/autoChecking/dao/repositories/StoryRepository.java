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

    //Selection
}
