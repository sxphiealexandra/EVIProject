package Animal; //Alexandra

import Animal.dto.AnimalFullDTO;
import Location.Location;
import Location.LocationRepository;
import Observation.Observation;
import Observation.ObservationRepository;
import Genus.Genus;
import Genus.GenusRepository;

import java.util.*;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GenusRepository genusRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ObservationRepository observationRepository;

    //Gibt alle Tiere zurück:
    public List<Animal> getAnimalList() {
        List<Animal> mylist = new ArrayList<>();
        animalRepository.findAll().forEach(mylist::add);
        return mylist;
    }
    
    //Holt einzelnes Tier anhand der id 
    public Animal getAnimal(long id) {
        return animalRepository.findById(id).orElse(null);
    }

    //Speichert neues Tier mit optionaler Gattung
    public void addAnimal(Animal animal) {
        Genus genus = animal.getGenus();
        if (genus != null && genus.getId() == 0) {
            genusRepository.save(genus); //Gattung vorher speichern falls neu
        }
        animal.setGenus(genus);
        animalRepository.save(animal); 
    }

    //Aktualisiert vorhandenes Tier
    public void updateAnimal(long id, Animal animal) {
        Genus genus = animal.getGenus();
        if (genus != null && genus.getId() == 0) {
            genusRepository.save(genus);
        }
        animal.setGenus(genus);
        animalRepository.save(animal);
    }
    
    //Löscht Tier anhand id
    public void deleteAnimal(long id) {
        animalRepository.deleteById(id);
    }

    //Fügt neues Tier inkl. Gattung, Ort und Beobachtung hinzu (Klasse aus package Animal.dto)
    public void addFullAnimal(AnimalFullDTO dto) {
    	Genus genus = genusRepository.findByLatinDesignation(dto.latinDesignation)
                .orElseGet(() -> {
                    Genus newGenus = new Genus(
                        dto.latinDesignation, dto.designation, dto.description,
                        dto.family, dto.diet, dto.reproductiveStrategy,
                        dto.activeCycle, dto.socialStructure, dto.lifeSpan
                    );
                    genusRepository.save(newGenus);
                    return newGenus;
                });

        Animal animal = new Animal(dto.gender, dto.estimatedAge, dto.estimatedSize, dto.estimatedWeight, genus);
        animalRepository.save(animal);

      

            // ✅ Bestehende Location suchen
            Location location = locationRepository.findByShortTitle(dto.locationShortTitle)
                .orElseGet(() -> {
                    // Wenn nicht gefunden: neue Location erzeugen
                    Location newLocation = new Location(dto.locationShortTitle, dto.locationDescription);
                    return locationRepository.save(newLocation);
                });

        Observation observation = new Observation(dto.date, dto.time, animal, location);
        observationRepository.save(observation);
    }
    
    
   

   //Aktualisiert vollständiges Tier inkl. abhängiger Daten
    public void updateFullAnimal(long id, AnimalFullDTO dto) {
        Animal existing = getAnimal(id);
        if (existing == null) return;
        
        //Tierdaten aktualisieren
        existing.setGender(dto.gender);
        existing.setEstimatedAge(dto.estimatedAge);
        existing.setEstimatedSize(dto.estimatedSize);
        existing.setEstimatedWeight(dto.estimatedWeight);

        //Genus aktualisieren
        Genus genus = existing.getGenus();
        if (genus == null) genus = new Genus();

        genus.setLatinDesignation(dto.latinDesignation);
        genus.setDesignation(dto.designation);
        genus.setDescription(dto.description);
        genus.setFamily(dto.family);
        genus.setDiet(dto.diet);
        genus.setReproductiveStrategy(dto.reproductiveStrategy);
        genus.setActiveCycle(dto.activeCycle);
        genus.setSocialStructure(dto.socialStructure);
        genus.setLifeSpan(dto.lifeSpan);
        genusRepository.save(genus);
        existing.setGenus(genus);
        animalRepository.save(existing);

     // Beobachtung und Ort aktualisieren
     // === 3. Beobachtung aktualisieren ===
        Observation obs = observationRepository.findByAnimalId(id).stream().findFirst().orElse(null);
        if (obs != null) {
            obs.setDate(dto.date);
            obs.setTime(dto.time);

            Location loc = obs.getLocation();

            if (loc != null) {
                // Nur bestehende Location updaten – KEIN neues Objekt erzeugen
                loc.setShortTitle(dto.locationShortTitle);
                loc.setDescription(dto.locationDescription);
                locationRepository.save(loc);
                obs.setLocation(loc); // bleibt dieselbe Instanz
            }

            observationRepository.save(obs);
        }


    }
    
    //Löscht Tier + zugehörige Beeobachtung + Location + Gattung
    public void deleteFullAnimal(long id) {
        System.out.println("🔍 Versuche Tier mit ID " + id + " zu löschen...");

        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            System.out.println("❌ Tier mit ID " + id + " nicht gefunden.");
            throw new IllegalArgumentException("Tier mit ID " + id + " wurde nicht gefunden.");
        }

        // Beobachtungen abrufen
        List<Observation> observations = observationRepository.findByAnimalId(id);
        Set<Location> relatedLocations = new HashSet<>();

        System.out.println("📋 Beobachtungen gefunden: " + observations.size());

        for (Observation obs : observations) {
            if (obs.getLocation() != null) {
                relatedLocations.add(obs.getLocation());
            }
            System.out.println("🗑️ Lösche Beobachtung ID: " + obs.getId());
            observationRepository.delete(obs);
        }

        // Ungenutzte Orte löschen
        for (Location loc : relatedLocations) {
            Location reloaded = locationRepository.findById(loc.getlNr()).orElse(null);
            if (reloaded != null && (reloaded.getObservations() == null || reloaded.getObservations().isEmpty())) {
                System.out.println("🗑️ Lösche unbenutzte Location ID: " + reloaded.getlNr());
                locationRepository.delete(reloaded);
            }
        }

        System.out.println("🗑️ Lösche Tier ID: " + animal.getId());
        animalRepository.delete(animal);

        // Gattung nur löschen, wenn sie von keinem weiteren Tier verwendet wird
        Genus genus = animal.getGenus();
        if (genus != null) {
            boolean stillUsed = StreamSupport.stream(animalRepository.findAll().spliterator(), false)
                    .anyMatch(a -> a.getGenus() != null && a.getGenus().getId() == genus.getId());

            if (!stillUsed) {
                System.out.println("🗑️ Lösche ungenutzte Gattung ID: " + genus.getId());
                genusRepository.delete(genus);
            }
        }

        System.out.println("✅ Tier mit ID " + id + " und alle zugehörigen Daten wurden erfolgreich gelöscht.");
    }
    
 // Holt ein vollständiges Tierobjekt mit Genus, Observation und Location
    public AnimalFullDTO getFullAnimal(long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) return null;

        AnimalFullDTO dto = new AnimalFullDTO();
        dto.gender = animal.getGender();
        dto.estimatedAge = animal.getEstimatedAge();
        dto.estimatedSize = animal.getEstimatedSize();
        dto.estimatedWeight = animal.getEstimatedWeight();

        Genus genus = animal.getGenus();
        if (genus != null) {
            dto.genusId = genus.getId(); //Genus-ID einfügen für tabelle wichtig
            dto.latinDesignation = genus.getLatinDesignation();
            dto.designation = genus.getDesignation();
            dto.description = genus.getDescription();
            dto.family = genus.getFamily();
            dto.diet = genus.getDiet();
            dto.reproductiveStrategy = genus.getReproductiveStrategy();
            dto.activeCycle = genus.getActiveCycle();
            dto.socialStructure = genus.getSocialStructure();
            dto.lifeSpan = genus.getLifeSpan();
        }

        Observation obs = observationRepository.findByAnimalId(id).stream().findFirst().orElse(null);
        if (obs != null) {
            dto.date = obs.getDate();
            dto.time = obs.getTime();
            Location location = obs.getLocation();
            if (location != null) {
                dto.locationLNr = location.getlNr(); //Location-ID einfügen für Tabelle wichtig 
                dto.locationShortTitle = location.getShortTitle();
                dto.locationDescription = location.getDescription();
            }
        }

        return dto;
    }

    
    public boolean deleteImageForAnimal(long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null || animal.getImage() == null) {
            return false; // Tier oder Bild existiert nicht
        }

        animal.setImage(null); // Bild auf null setzen
        animalRepository.save(animal); // Tier aktualisieren ohne Bild

        return true;
    }
    
    



}