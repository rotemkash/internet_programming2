package controller;

import jakarta.servlet.http.HttpSession;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.UserRepository;

/**
 * Controller class for handling user authentication.
 * This class provides endpoints for user login and logout operations.
 */
@Controller
public class LoginController {

    private final UserRepository userRepository;

    /**
     * Constructs a LoginController with the necessary repository.
     *
     * @param userRepository The repository for user operations.
     */
    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the request for the login form.
     *
     * @return The name of the view to render for the login form.
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Handles the login request.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @param session The HTTP session to store user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        // Check for admin login
        if (username.equals("Admin1") && password.equals("Admin1")) {
            // Check if the admin user already exists in the database
            User adminUser = userRepository.findByUsername("Admin1");
            if (adminUser == null) {
                adminUser = new User();
                adminUser.setUsername("Admin1");
                adminUser.setEmail("admin1@gmail.com");
                adminUser.setPassword("Admin1"); // Assuming you set a password for the admin user
                adminUser.setAdmin(true);
                userRepository.save(adminUser); // Save the admin user to the database
            }
            session.setAttribute("currentUser", adminUser);
            return "redirect:/chat-rooms";
        }

        // Regular user login
        User user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("usernameError", "Invalid username");
            return "login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("passwordError", "Invalid password");
            return "login";
        } else {
            session.setAttribute("currentUser", user);
            return "redirect:/chat-rooms";
        }
    }

    /**
     * Handles the logout request.
     *
     * @param session The HTTP session to be invalidated.
     * @return A string indicating where to redirect after the operation.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}