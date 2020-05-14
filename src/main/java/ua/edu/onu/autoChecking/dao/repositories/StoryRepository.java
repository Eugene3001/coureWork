package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

public interface StoryRepository extends CrudRepository<Story, StoryId> {
}
