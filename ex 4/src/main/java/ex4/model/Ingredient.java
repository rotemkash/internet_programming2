package ex4.model;

/**
 * Ingredient class representing a pizza ingredient with its name and image URL.
 */
public class Ingredient {
    private String name;
    private String imageUrl;

    /**
     * Default constructor for Ingredient.
     */
    public Ingredient() {
    }

    /**
     * Parameterized constructor for Ingredient.
     * @param name The name of the ingredient.
     * @param imageUrl The image URL of the ingredient.
     */
    public Ingredient(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the name of the ingredient.
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     * @param name The name of the ingredient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the image URL of the ingredient.
     * @return The image URL of the ingredient.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the ingredient.
     * @param imageUrl The image URL of the ingredient.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
