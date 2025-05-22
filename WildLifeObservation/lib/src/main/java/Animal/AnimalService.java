package Animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Diese Klasse übernimmt die Logik rund um das Tier-Handling

public class AnimalService {
	@Autowired
	private AnimalRepository animalRepository;
	
	//Gibt eine Liste aller Tiere im System zurück.
	public List<Animal> getAnimalList() {
		ArrayList<Animal> mylist= new ArrayList<>();
		Iterator<Animal> it = animalRepository.findAll().iterator();
		while(it.hasNext())
		mylist.add(it.next());
		return mylist;
		}
	
	
		/**
		 * Holt ein einzelnes Tier anhand seiner ID.
		 * @param id Tier-ID
		 * @return Animal oder null
		 */
		public Animal getAnimal(long id) {
		return animalRepository.findById(id).orElse(null);
		}
		
		/**
	     * Fügt ein neues Tier hinzu.
	     * @param animal zu speicherndes Tier
	     */	
		public void addAnimal(Animal animal) {
		animalRepository.save(animal);
		}
		//Aktualisiert ein bestehendes Tier.
	
		public void updateAnimal(long id, Animal animal) {
		animalRepository.save(animal);
		}
		// Löscht ein Tier anhand der ID.
		public void deleteAnimal(long id) {
		animalRepository.deleteById(id);
		}
}
