package Observation;

import org.springframework.data.repository.CrudRepository;

// Repository für Zugriff auf Beobachtungsdaten - Alexandra
public interface ObservationRepository extends CrudRepository<Observation, Long> {
    // Erweiterbar: z.B. findByAnimalId(Long id) etc.
}
