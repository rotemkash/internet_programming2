package ex4.controller;

import ex4.model.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * IngredientController class that handles the ingredients API endpoints.
 */
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    /**
     * Handles HTTP GET requests to "/api/ingredients".
     * @return A list of predefined ingredients.
     */
    @GetMapping
    public List<Ingredient> getIngredients() {
        return Arrays.asList(
                new Ingredient("Cheese", "/images/cheese.png"),
                new Ingredient("Pepperoni", "/images/pepperoni.png"),
                new Ingredient("Mushrooms", "/images/mushrooms.png"),
                new Ingredient("Onions", "/images/onions.png"),
                new Ingredient("Corn", "/images/corn.png"),
                new Ingredient("Olives", "/images/olives.png")
        );
    }
}
