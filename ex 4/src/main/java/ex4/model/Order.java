package ex4.model;

import java.util.List;

/**
 * Order class representing a pizza order with user details and a list of pizzas.
 */
public class Order {
    private String code;
    private String firstName;
    private String lastName;
    private ex4.model.Address address;
    private String phoneNumber;
    private List<Pizza> pizzas;

    /**
     * Default constructor for Order.
     */
    public Order() {
    }

    /**
     * Parameterized constructor for Order.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param address The address of the user.
     * @param phoneNumber The phone number of the user.
     * @param pizzas The list of pizzas in the order.
     */
    public Order(String firstName, String lastName, ex4.model.Address address, String phoneNumber, List<Pizza> pizzas) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pizzas = pizzas;
    }

    /**
     * Gets the unique code of the order.
     * @return The unique code of the order.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the unique code of the order.
     * @param code The unique code of the order.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the first name of the user.
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the address of the user.
     * @return The address of the user.
     */
    public ex4.model.Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     * @param address The address of the user.
     */
    public void setAddress(ex4.model.Address address) {
        this.address = address;
    }

    /**
     * Gets the phone number of the user.
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * @param phoneNumber The phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the list of pizzas in the order.
     * @return The list of pizzas in the order.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Sets the list of pizzas in the order.
     * @param pizzas The list of pizzas in the order.
     */
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
