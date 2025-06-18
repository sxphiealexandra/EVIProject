package Genus;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Kenan - Repository-Interface für die Genus-Entität.
 * Ermöglicht CRUD-Operationen (Create, Read, Update, Delete) über Spring Data JPA.
 */
@Repository
public interface GenusRepository extends CrudRepository<Genus, Long> {
	Optional<Genus> findByLatinDesignation(String latinDesignation); 
	
    // Eigene Query-Methoden (Derived Queries) können hier ergänzt werden - Kenan
    

}