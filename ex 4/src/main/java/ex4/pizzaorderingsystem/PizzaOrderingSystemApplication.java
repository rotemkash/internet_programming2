package ex4.pizzaorderingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Pizza Ordering System Spring Boot application.
 */
@SpringBootApplication(scanBasePackages = "ex4")
public class PizzaOrderingSystemApplication {

    /**
     * Main method to run the Spring Boot application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(PizzaOrderingSystemApplication.class, args);
    }
}
