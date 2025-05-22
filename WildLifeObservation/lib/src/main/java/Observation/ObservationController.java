package Observation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//REST-Controller für Beobachtungen
 
@RestController
@RequestMapping("/observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    @GetMapping("/{id}")
    public Observation getObservation(@PathVariable Long id) {
        return observationService.getObservation(id);
    }

    @PostMapping
    public void addObservation(@RequestBody Observation observation) {
        observationService.addObservation(observation);
    }

    @PutMapping("/{id}")
    public void updateObservation(@PathVariable Long id, @RequestBody Observation observation) {
        observationService.updateObservation(id, observation);
    }

    @DeleteMapping("/{id}")
    public void deleteObservation(@PathVariable Long id) {
        observationService.deleteObservation(id);
    }
}
