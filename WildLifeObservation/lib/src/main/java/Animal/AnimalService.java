package Animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AnimalService {
	@Autowired
	private AnimalRepository animalRepository;
	
	public List<Animal> getAnimalList() {
		ArrayList<Animal> mylist= new ArrayList<>();
		Iterator<Animal> it = animalRepository.findAll().iterator();
		while(it.hasNext())
		mylist.add(it.next());
		return mylist;
		}
		public Animal getAnimal(long id) {
		return animalRepository.findById(id).orElse(null);
		}
		public void addAnimal(Animal animal) {
		animalRepository.save(animal);
		}
		public void updateAnimal(long id, Animal animal) {
		animalRepository.save(animal);
		}
		public void deleteAnimal(long id) {
		animalRepository.deleteById(id);
		}
}
