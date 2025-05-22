package Genus;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-Interface für die Genus-Entität.
 * Ermöglicht CRUD-Operationen (Create, Read, Update, Delete) über Spring Data JPA.
 */
@Repository
public interface GenusRepository extends CrudRepository<Genus, Long> {
    
    // Eigene Query-Methoden (Derived Queries) können hier ergänzt werden, z. B.:
    // List<Genus> findByFamily(String family);
    // Optional<Genus> findByLatinDesignation(String latinDesignation);

}