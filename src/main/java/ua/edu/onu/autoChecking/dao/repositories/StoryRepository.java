package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, StoryId> {
    //Sorting
    @Query("select s from Story s order by s.start_date asc")
    List<Story> getStartDateSortedList();

    @Query("select s from Story s order by s.finish_date desc")
    List<Story> getFinishDateSortedList();

    //Selection
}
