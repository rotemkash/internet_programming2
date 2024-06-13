package ex4.model;

/**
 * Address class representing the address details of an order.
 */
public class Address {
    private String street;
    private String houseNumber;
    private String city;

    /**
     * Default constructor for Address.
     */
    public Address() {
    }

    /**
     * Parameterized constructor for Address.
     * @param street The street of the address.
     * @param houseNumber The house number of the address.
     * @param city The city of the address.
     */
    public Address(String street, String houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    /**
     * Gets the street of the address.
     * @return The street of the address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     * @param street The street of the address.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the house number of the address.
     * @return The house number of the address.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the house number of the address.
     * @param houseNumber The house number of the address.
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets the city of the address.
     * @return The city of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     * @param city The city of the address.
     */
    public void setCity(String city) {
        this.city = city;
    }
}
