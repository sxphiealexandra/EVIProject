package Animal; // Alexandra

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/animal") // WICHTIG: Basis-URL für alle Endpunkte
public class AnimalController {

    @Autowired
    AnimalService animalService;

    // Gibt eine Liste aller Tiere zurück
    @GetMapping
    public List<Animal> getAnimalList() {
        return animalService.getAnimalList();
    }

    // Holt ein Tier anhand dessen ID
    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") long id) {
        return animalService.getAnimal(id);
    }

    // Fügt ein Tier hinzu (ohne Bild)
    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
    }

    // Aktualisiert ein Tier über ID
    @PutMapping("/{id}")
    public void updateAnimal(@PathVariable("id") long id, @RequestBody Animal animal) {
        animalService.updateAnimal(id, animal);
    }

    // Löscht ein Tier über ID
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") long id) {
        animalService.deleteAnimal(id);
    }

    // Bild hochladen (optional)
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
            animalService.addAnimal(animal); // Speichert Tier mit Bild

            return ResponseEntity.ok("Bild erfolgreich hochgeladen.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Fehler beim Hochladen des Bildes.");
        }
    }

    // Bild abrufen
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        Animal animal = animalService.getAnimal(id);
        if (animal == null || animal.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE) // Oder IMAGE_PNG_VALUE
                .body(animal.getImage());
    }
}
