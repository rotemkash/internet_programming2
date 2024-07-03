package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling home page requests.
 * This class provides an endpoint for the application's home page.
 */
@Controller
public class HomeController {

    /**
     * Handles the request for the home page.
     *
     * @return The name of the view to render for the home page.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}