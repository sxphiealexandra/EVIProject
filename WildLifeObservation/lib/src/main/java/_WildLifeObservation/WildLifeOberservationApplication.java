package _WildLifeObservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "_WildLifeObservation", // dein Hauptpackage
        "Genus", "Animal", "Location", "Observation" // die anderen Komponenten
})
@EnableJpaRepositories(basePackages = {"Genus", "Animal", "Location", "Observation"})
@EntityScan(basePackages = {"Genus", "Animal", "Location", "Observation"})
public class WildLifeOberservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WildLifeOberservationApplication.class, args);
    }
}