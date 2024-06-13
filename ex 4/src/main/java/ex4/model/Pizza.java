package ex4.model;

import java.util.List;

/**
 * Pizza class representing a pizza with a list of ingredients and its price.
 */
public class Pizza {
    private List<Ingredient> ingredients;
    private double price;

    /**
     * Default constructor for Pizza.
     */
    public Pizza() {
    }

    /**
     * Parameterized constructor for Pizza.
     * @param ingredients The list of ingredients on the pizza.
     * @param price The price of the pizza.
     */
    public Pizza(List<Ingredient> ingredients, double price) {
        this.ingredients = ingredients;
        this.price = price;
    }

    /**
     * Gets the list of ingredients on the pizza.
     * @return The list of ingredients on the pizza.
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of ingredients on the pizza.
     * @param ingredients The list of ingredients on the pizza.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the price of the pizza.
     * @return The price of the pizza.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the pizza.
     * @param price The price of the pizza.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Calculates the price of the pizza based on a base price and ingredient price.
     * @param basePrice The base price of the pizza.
     * @param ingredientPrice The price per ingredient.
     */
    public void calculatePrice(double basePrice, double ingredientPrice) {
        this.price = basePrice + (ingredients.size() * ingredientPrice);
    }
}
