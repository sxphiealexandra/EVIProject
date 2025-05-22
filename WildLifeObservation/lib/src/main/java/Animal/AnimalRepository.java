package Animal;

import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository <Animal, Long> {

}
