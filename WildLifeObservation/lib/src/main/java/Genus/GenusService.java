package Genus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Service-Klasse zur Verarbeitung von Geschäftslogik für die Genus-Entität.
 * Verwendet das GenusRepository zur Kommunikation mit der Datenbank.
 */
@Service
public class GenusService {

    @Autowired
    private GenusRepository genusRepository;

    /**
     * Gibt alle gespeicherten Gattungen zurück.
     * Verwendet Repository und wandelt Iterable in eine Liste um.
     */
    public List<Genus> getAllGenus() {
        List<Genus> list = new ArrayList<>();
        genusRepository.findAll().forEach(list::add); // Lambda-Ausdruck: jedes Element hinzufügen
        return list;
    }

    /**
     * Gibt eine bestimmte Gattung anhand ihrer ID zurück.
     * Gibt null zurück, wenn keine gefunden wurde.
     */
    public Genus getGenus(long id) {
        return genusRepository.findById(id).orElse(null);
    }
    
    
    public Genus findByLatinDesignation(String latinName) {
        return genusRepository.findByLatinDesignation(latinName).orElse(null);
    }

    /**
     * Fügt eine neue Gattung zur Datenbank hinzu.
     */
    public void addGenus(Genus genus) {
        genusRepository.save(genus);
    }

    /**
     * Aktualisiert eine bestehende Gattung.
     * Die ID wird gesetzt, um sicherzustellen, dass es sich um ein Update handelt.
     */
    public void updateGenus(long id, Genus genus) {
        genus.setId(id);
        genusRepository.save(genus); // Spring Data JPA führt ein "upsert" durch (update oder insert)
    }

    /**
     * Löscht eine Gattung anhand ihrer ID.
     */
    public void deleteGenus(long id) {
        genusRepository.deleteById(id);
    }
    
}