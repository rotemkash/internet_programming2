package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Represents a comment on a message in the application.
 * This class is an entity that maps to the database table for comments.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The content of the comment.
     * Must be between 2 and 120 characters long and cannot be blank.
     */
    @Column(nullable = false)
    @NotBlank(message = "Comment cannot be empty")
    @Size(min = 2, max = 120, message = "Comment must be between 2 and 120 characters")
    private String content;

    /**
     * The timestamp when the comment was created.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * The user who created the comment.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The message this comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    /**
     * Indicates whether the comment has been edited.
     */
    @Column(nullable = false)
    private boolean edited = false;

    // Getters and setters

    /**
     * Gets the ID of the comment.
     * @return the comment ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     * @param id the comment ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the content of the comment.
     * @return the comment content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     * @param content the comment content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp of when the comment was created.
     * @return the comment timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the comment was created.
     * @param timestamp the comment timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user who created the comment.
     * @return the user who created the comment
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who created the comment.
     * @param user the user to set as the creator of the comment
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the message this comment belongs to.
     * @return the message this comment belongs to
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Sets the message this comment belongs to.
     * @param message the message to set for this comment
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Checks if the comment has been edited.
     * @return true if the comment has been edited, false otherwise
     */
    public boolean isEdited() {
        return edited;
    }

    /**
     * Sets whether the comment has been edited.
     * @param edited true if the comment has been edited, false otherwise
     */
    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}