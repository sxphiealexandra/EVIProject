package Animal.dto;

import Animal.AnimalService;
import Animal.dto.AnimalFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal-full")
public class AnimalFullController {

    @Autowired
    private AnimalService animalService;

    // === Tier inkl. Genus + Observation + Location speichern ===
    @PostMapping
    public void addFullAnimal(@RequestBody AnimalFullDTO dto) {
        animalService.addFullAnimal(dto);
    }

    // === Tier vollständig aktualisieren ===
    @PutMapping("/{id}")
    public void updateFullAnimal(@PathVariable("id") long id, @RequestBody AnimalFullDTO dto) {
        animalService.updateFullAnimal(id, dto);
    }

    // === Tier vollständig löschen ===
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFullAnimal(@PathVariable("id") long id) {
        try {
            System.out.println("🧨 Lösche komplettes Tier mit ID: " + id);
            animalService.deleteFullAnimal(id);
            return ResponseEntity.ok("✅ Tier erfolgreich gelöscht.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Interner Fehler: " + e.getMessage());
        }
    }

    // === Tier vollständig abrufen (für edit-loader.html) ===
    @GetMapping("/{id}")
    public ResponseEntity<AnimalFullDTO> getFullAnimal(@PathVariable("id") long id) {
        AnimalFullDTO dto = animalService.getFullAnimal(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dto);
    }
}
