package Location;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository <Location, Long> {
	Optional<Location> findByShortTitle(String shortTitle); 

}
