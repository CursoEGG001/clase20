package net.local.demo.Biblio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the entry point of the DemoBiblio application. It uses Spring Boot to simplify application setup and
 * configuration.
 */
@SpringBootApplication

@RestController
public class DemoBiblioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBiblioApplication.class, args);
    }

}
