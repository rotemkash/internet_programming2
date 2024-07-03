package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system.
 * This class is an entity that maps to the 'User' table in the database.
 */
@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * Must start with an uppercase letter and contain only letters and numbers.
     */
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,25}", message = "Username must start with an uppercase letter and contain only letters and numbers")
    private String username;

    /**
     * The email address of the user.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * The password of the user.
     * Must be at least 6 characters long.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    /**
     * Indicates whether the user has admin privileges.
     */
    private boolean isAdmin;

    /**
     * The list of forums created by the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms = new ArrayList<>();

    /**
     * Gets the user's unique identifier.
     *
     * @return the user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the user has admin privileges.
     *
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets the user's admin status.
     *
     * @param admin the admin status to set
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Gets the list of forums created by the user.
     *
     * @return the list of forums
     */
    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    /**
     * Sets the list of forums created by the user.
     *
     * @param chatRooms the list of forums to set
     */
    public void setChatRooms(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }
}