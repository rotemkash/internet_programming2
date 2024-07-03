package repository;

import model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Forum operations.
 * This interface provides CRUD operations for Forum entities.
 */
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    /**
     * Checks if a forum with the given name exists (case-insensitive).
     *
     * @param name The name of the forum to check.
     * @return true if a forum with the given name exists, false otherwise.
     */
    boolean existsByNameIgnoreCase(String name);
}