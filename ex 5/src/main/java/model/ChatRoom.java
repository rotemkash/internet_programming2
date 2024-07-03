package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a forum in the application.
 * This class is an entity that maps to the database table for forums.
 */
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the forum, which represents the main question or topic.
     * Must be between 2 and 120 characters long.
     */
    @Size(min = 2, max = 120, message = "Question must contain between 2 and 120 characters")
    @Column(nullable = false)
    @NotBlank(message = "Question cannot be empty")
    private String name;

    /**
     * The list of messages in this forum.
     */
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    /**
     * The user who created this forum.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters

    /**
     * Gets the ID of the forum.
     * @return the forum ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the forum.
     * @param id the forum ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the forum.
     * @return the forum name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the forum.
     * @param name the forum name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of messages in the forum.
     * @return the list of messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the list of messages in the forum.
     * @param messages the list of messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Gets the user who created the forum.
     * @return the user who created the forum
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who created the forum.
     * @param user the user to set as the creator of the forum
     */
    public void setUser(User user) {
        this.user = user;
    }
}