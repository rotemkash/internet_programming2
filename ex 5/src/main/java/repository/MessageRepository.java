package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Message;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Message operations.
 * This interface provides CRUD operations for Message entities.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}