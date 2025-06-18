package Genus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;

import java.util.List;

/**
 * REST-Controller für die Verwaltung von Genus-Entitäten.
 * Bietet Endpunkte zur Erstellung, Abfrage, Aktualisierung und Löschung von Gattungen.
 */
@RestController
@RequestMapping("/genus") // Basis-URL für alle Endpunkte dieser Klasse
public class GenusController {

    @Autowired
    private GenusService genusService;
    
    /**
     * Gibt eine Liste aller Gattungen zurück.
     * GET /genus - Kenan
     */
    @GetMapping
    public List<Genus> getAllGenus() {
        return genusService.getAllGenus();
    }

    /**
     * Gibt eine einzelne Gattung anhand ihrer ID zurück.
     * GET /genus/{id} - Kenan
     */
    @GetMapping("/{id}")
    public Genus getGenus(@PathVariable long id) {
        return genusService.getGenus(id);
    }
    
    
    /**wird von create/edit-loader Verwendet für Erkennungsfunktion des Genus 
     * über Lateinische Bezeichnung - Alexandra */

    @GetMapping("/by-latin/{latinName}")
    public Genus getByLatinName(@PathVariable String latinName) {
        return genusService.findByLatinDesignation(latinName);
    } 
    
    
    /**
     * Fügt eine neue Gattung zur Datenbank hinzu.
     * POST /genus
     */
    @PostMapping
    public void addGenus(@RequestBody Genus genus) {
        genusService.addGenus(genus);
    }

    /**
     * Aktualisiert eine bestehende Gattung mit den übergebenen Daten.
     * PUT /genus/{id} -Kenan
     */
    @PutMapping("/{id}")
    public void updateGenus(@PathVariable long id, @RequestBody Genus genus) {
        genusService.updateGenus(id, genus);
    }

    /**
     * Löscht eine Gattung anhand ihrer ID.
     * DELETE /genus/{id} - Kenan
     */
    @DeleteMapping("/{id}")
    public void deleteGenus(@PathVariable long id) {
        genusService.deleteGenus(id);
    }

}