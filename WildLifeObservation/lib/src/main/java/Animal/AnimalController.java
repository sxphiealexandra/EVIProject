package Animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class AnimalController {
	@Autowired
	AnimalService animalService;
	
	
	@RequestMapping("/animals")
	public List<Animal> getAnimalList() {
		return animalService.getAnimalList();
	}
	@RequestMapping("/animals/{id}")
	public Animal getAnimal(@PathVariable("id") long id) {
		return animalService.getAnimal(id);
		
	}
	@PostMapping(value="/animals")
	public void addAnimal(@RequestBody Animal animal) {
		animalService.addAnimal(animal);
	}
	@PutMapping("/animals/{id}")
	public void updateAnimal(@PathVariable("id")long id,@RequestBody Animal animal) {
		animalService.updateAnimal(id, animal);
	}
	@DeleteMapping("/animals/{id}")
	public void deleteAnimal(@PathVariable("id")long id) {
		animalService.deleteAnimal(id);
	}
}
