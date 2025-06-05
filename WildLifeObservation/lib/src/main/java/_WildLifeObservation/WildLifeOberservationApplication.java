package _WildLifeObservation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hauptklasse der Spring Boot-Anwendung.
 * Sie startet die gesamte Anwendung und steuert das Scanning von Komponenten, Repositories und JPA-Entitäten.
 */
@SpringBootApplication(
        // Gibt an, welche Packages Spring Boot nach @Component, @Service, @Controller usw. durchsuchen soll
        scanBasePackages = {
                "_WildLifeObservation", // Hauptpackage (enthält die Main-Klasse)
                "Genus",                // Enthält GenusController, -Service, -Repository
                "Animal",               // Enthält AnimalController, -Service, -Repository
                "Location",             // Enthält LocationController, -Service, -Repository
                "Observation"           // Enthält ObservationController, -Service, -Repository
        }
)

// Aktiviert die automatische Erkennung von Spring Data JPA Repositories in den angegebenen Packages
@EnableJpaRepositories(basePackages = {
        "Genus", "Animal", "Location", "Observation"
})

// Sagt Spring, in welchen Packages nach @Entity-Klassen (Datenbanktabellen) gesucht werden soll
@EntityScan(basePackages = {
        "Genus", "Animal", "Location", "Observation"
})
public class WildLifeOberservationApplication {

    /**
     * Einstiegspunkt der Anwendung.
     * SpringApplication.run(...) startet den eingebetteten Server und die gesamte Spring Boot App.
     */
    public static void main(String[] args) {
        SpringApplication.run(WildLifeOberservationApplication.class, args);
    }
}