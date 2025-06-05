package Animal; //Alexandra

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Genus.Genus;
import Genus.GenusRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GenusRepository genusRepository;

    // Gibt eine Liste aller Tiere im System zurück
    public List<Animal> getAnimalList() {
        ArrayList<Animal> mylist = new ArrayList<>();
        Iterator<Animal> it = animalRepository.findAll().iterator();
        while (it.hasNext())
            mylist.add(it.next());
        return mylist;
    }

    // Holt ein einzelnes Tier anhand seiner ID
    public Animal getAnimal(long id) {
        return animalRepository.findById(id).orElse(null);
    }

    // Fügt ein neues Tier hinzu, inkl. neuem Genus bei Bedarf
    public void addAnimal(Animal animal) {
        Genus genus = animal.getGenus();

        if (genus != null && genus.getId() == 0) {
            // Kein vorhandener Genus → neu speichern
            genusRepository.save(genus);
        }

        animal.setGenus(genus);
        animalRepository.save(animal);
    }

    // Aktualisiert ein bestehendes Tier
    public void updateAnimal(long id, Animal animal) {
        Genus genus = animal.getGenus();

        if (genus != null && genus.getId() == 0) {
            genusRepository.save(genus);
        }

        animal.setGenus(genus);
        animalRepository.save(animal);
    }

    // Löscht ein Tier anhand der ID
    public void deleteAnimal(long id) {
        animalRepository.deleteById(id);
    }
}
