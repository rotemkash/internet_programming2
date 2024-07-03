package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a message in a forum in the application.
 * This class is an entity that maps to the database table for messages.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The content of the message.
     * Must be between 2 and 250 characters long.
     */
    @Column(nullable = false)
    @Size(min = 2, max = 250, message = "Message must be between 2 and 400 characters")
    @NotBlank(message = "Message cannot be empty")
    private String content;

    /**
     * The timestamp when the message was created.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * The user who created the message.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The forum this message belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    /**
     * Indicates whether the message has been edited.
     */
    @Column(nullable = false)
    private boolean edited = false;

    /**
     * The list of comments on this message.
     */
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Getters and setters

    /**
     * Gets the ID of the message.
     * @return the message ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the message.
     * @param id the message ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the content of the message.
     * @return the message content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message.
     * @param content the message content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp of when the message was created.
     * @return the message timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the message was created.
     * @param timestamp the message timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user who created the message.
     * @return the user who created the message
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who created the message.
     * @param user the user to set as the creator of the message
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the forum this message belongs to.
     * @return the forum this message belongs to
     */
    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    /**
     * Sets the forum this message belongs to.
     * @param chatRoom the forum to set for this message
     */
    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    /**
     * Checks if the message has been edited.
     * @return true if the message has been edited, false otherwise
     */
    public boolean isEdited() {
        return edited;
    }

    /**
     * Sets whether the message has been edited.
     * @param edited true if the message has been edited, false otherwise
     */
    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    /**
     * Gets the list of comments on this message.
     * @return the list of comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments on this message.
     * @param comments the list of comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}