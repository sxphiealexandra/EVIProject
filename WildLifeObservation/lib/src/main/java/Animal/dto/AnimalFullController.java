/**
 * Daten-Transfer-Objekt (DTO) für das vollständige Tiermodell.
 * 
 * Klasse bündelt alle Informationen, die für das Erstellen oder Bearbeiten eines Tiers benötigt werden.
 * Dazu gehören:
 * - Tierdaten (Animal): Geschlecht, Alter, Größe, Gewicht
 * - Gattungsdaten (Genus): wissenschaftliche & deutsche Bezeichnung, Beschreibung, Familie, Ernährung etc.
 * - Beobachtungsdaten (Observation) mit Ort (Location): Datum, Uhrzeit, Kurzbeschreibung und Beschreibung des Orts
 * 
 * Wird im Frontend-Formular verwendet, um alle Eingaben zu sammeln
 * und in einem einzigen Request an das Backend zu senden. - Alexandra
 */
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

    //Tier inkl. Genus + Observation + Location speichern
    @PostMapping
    public void addFullAnimal(@RequestBody AnimalFullDTO dto) {
        animalService.addFullAnimal(dto);
    }

    //Tier vollständig aktualisieren (für edit-loader.html)
    @PutMapping("/{id}")
    public void updateFullAnimal(@PathVariable("id") long id, @RequestBody AnimalFullDTO dto) {
        animalService.updateFullAnimal(id, dto);
    }

    //Tier vollständig löschen (inklusive alle abhängigen Daten dazu)
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

    // Tier vollständig abrufen (für edit-loader.html)
    @GetMapping("/{id}")
    public ResponseEntity<AnimalFullDTO> getFullAnimal(@PathVariable("id") long id) {
        AnimalFullDTO dto = animalService.getFullAnimal(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dto);
    }
}
