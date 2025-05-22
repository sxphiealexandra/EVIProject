package Observation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service-Schicht für Beobachtungen.
 */
@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public List<Observation> getAllObservations() {
        List<Observation> list = new ArrayList<>();
        observationRepository.findAll().forEach(list::add);
        return list;
    }

    public Observation getObservation(Long id) {
        return observationRepository.findById(id).orElse(null);
    }

    public void addObservation(Observation observation) {
        observationRepository.save(observation);
    }

    public void updateObservation(Long id, Observation updated) {
        updated.setId(id);
        observationRepository.save(updated);
    }

    public void deleteObservation(Long id) {
        observationRepository.deleteById(id);
    }
}
