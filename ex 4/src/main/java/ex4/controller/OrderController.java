package ex4.controller;

import ex4.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * OrderController class to handle requests related to pizza orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Map<String, Order> orders = new HashMap<>();

    /**
     * Places a new order.
     *
     * @param order The order to be placed.
     * @return The unique code of the placed order.
     * @throws IllegalArgumentException if any of the order details are invalid.
     */
    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        // Add validation here
        if (order.getFirstName().isEmpty() || order.getLastName().isEmpty() ||
                order.getAddress().getStreet().isEmpty() || order.getAddress().getHouseNumber().isEmpty() ||
                order.getAddress().getCity().isEmpty() || order.getPhoneNumber().length() != 10 || order.getPizzas().isEmpty()) {
            throw new IllegalArgumentException("Invalid order data");
        }

        String code = UUID.randomUUID().toString();
        order.setCode(code);
        orders.put(code, order);
        return code;
    }

    /**
     * Retrieves an order by its unique code.
     *
     * @param code The unique code of the order to retrieve.
     * @return The order corresponding to the provided code.
     * @throws IllegalArgumentException if the order with the specified code is not found.
     */
    @GetMapping("/{code}")
    public Order getOrder(@PathVariable String code) {
        Order order = orders.get(code);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        return order;
    }
}
