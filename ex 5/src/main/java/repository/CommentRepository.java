package repository;

import model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Comment operations.
 * This interface provides CRUD operations for Comment entities.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}