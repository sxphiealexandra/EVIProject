package Animal; // Alexandra

import Animal.dto.AnimalFullDTO;
import Genus.Genus;
import Location.Location;
import Observation.Observation;
import Observation.ObservationRepository;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/animal") // Basis-URL
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    ObservationRepository observationRepository;

    // === Alle Tiere abrufen ===
    @GetMapping
    public List<Animal> getAnimalList() {
        return animalService.getAnimalList();
    }

    // === Einzelnes Tier abrufen ===
    @GetMapping("/by-id/{id}")
    public Animal getAnimal(@PathVariable("id") long id) {
        return animalService.getAnimal(id);
    }

    // === Tier speichern (einfach) ===
    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
    }

    // === Tier aktualisieren ===
    @PutMapping("/{id}")
    public void updateAnimal(@PathVariable("id") long id, @RequestBody Animal animal) {
        animalService.updateAnimal(id, animal);
    }

    // === Tier löschen ===
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") long id) {
        animalService.deleteAnimal(id);
    }

    // === Bild hochladen ===
    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(
            @PathVariable("id") long id,
            @RequestParam("file") MultipartFile file) {
        try {
            Animal animal = animalService.getAnimal(id);
            if (animal == null) {
                return ResponseEntity.notFound().build();
            }
            animal.setImage(file.getBytes());
            animalService.addAnimal(animal);
            return ResponseEntity.ok("Bild erfolgreich hochgeladen.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Fehler beim Hochladen des Bildes.");
        }
    }

    // === Bild abrufen ===
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        Animal animal = animalService.getAnimal(id);
        if (animal == null || animal.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(animal.getImage());
    }
}
