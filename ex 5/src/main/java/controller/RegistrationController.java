package controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repository.UserRepository;

/**
 * Controller class for handling user registration.
 * This class provides endpoints for user registration operations.
 */
@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    /**
     * Constructs a RegistrationController with the necessary repository.
     *
     * @param userRepository The repository for user operations.
     */
    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the request for the registration form.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render for the registration form.
     */
    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles the user registration request.
     *
     * @param user The user object to be registered.
     * @param bindingResult The result of the validation.
     * @param confirmPassword The password confirmation.
     * @param session The HTTP session to store user information.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           @ModelAttribute("confirmPassword") String confirmPassword,
                           HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("confirmPasswordError", "Passwords do not match");
            return "register";
        }

        // Check if user is admin
        if (user.getUsername().equals("Admin1") && user.getEmail().equals("admin1@gmail.com") && user.getPassword().equals("Admin1")) {
            user.setAdmin(true);
            session.setAttribute("currentUser", user);
            return "redirect:/chat-rooms";
        }

        // For non-admin users, perform the usual checks
        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
            return "register";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
            return "register";
        }

        if (user.getUsername().equals(user.getPassword())) {
            bindingResult.rejectValue("password", "error.user", "Username and password must be different");
            return "register";
        }

        // Save the new user
        User savedUser = userRepository.save(user);

        // Use setId method
        user.setId(savedUser.getId());

        session.setAttribute("currentUser", user);
        return "redirect:/chat-rooms";
    }
}