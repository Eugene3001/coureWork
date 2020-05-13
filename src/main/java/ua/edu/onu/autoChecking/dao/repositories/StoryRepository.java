package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.onu.autoChecking.dao.entities.Story;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

public interface StoryRepository extends JpaRepository<Story, StoryId> {
}
