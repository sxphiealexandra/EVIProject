package Observation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Repository für Zugriff auf Beobachtungsdaten - Alexandra
public interface ObservationRepository extends CrudRepository<Observation, Long> {
	List<Observation> findByAnimalId(Long animalId);
}
