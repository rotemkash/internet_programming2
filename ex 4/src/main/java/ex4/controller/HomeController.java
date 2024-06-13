package ex4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController class to handle the root endpoint of the application.
 */
@RestController
public class HomeController {

    /**
     * Handles GET requests to the root URL ("/").
     * @return A welcome message for the Pizza App.
     */
    @GetMapping("/")
    public String home() {
        return "Welcome to the Pizza App!";
    }
}
