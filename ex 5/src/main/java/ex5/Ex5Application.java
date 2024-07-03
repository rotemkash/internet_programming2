package ex5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main application class for the Ex5 project.
 * This class configures and launches the Spring Boot application.
 * The class is annotated with:
 * - SpringBootApplication: Enables autoconfiguration and component scanning
 *   for the specified packages ("controller", "model", "repository").
 * - EntityScan: Specifies the base package ("model") to scan for JPA entities.
 * - EnableJpaRepositories: Enables JPA repositories for the specified package ("repository").
 */
@SpringBootApplication(scanBasePackages = {"controller", "model", "repository"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"repository"})
public class Ex5Application {

    /**
     * The main method which serves as the entry point for the application.
     *
     * @param args Command line arguments passed to the application (not used in this case).
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex5Application.class, args);
    }
}