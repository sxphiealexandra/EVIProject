/** REST-Controller für vollständige Tieroperationen 
 * (Animal + Genus + Observation + Location).
 * -Alexaandra
 * */
package Animal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AnimalFullDTO {
	
	//Animal Daten/Infos
    public String gender;
    public int estimatedAge;
    public double estimatedSize;
    public double estimatedWeight;

    // Genus-Infos/Daten
    public String latinDesignation;
    public String designation;
    public String description;
    public String family;
    public String diet;
    public String reproductiveStrategy;
    public String activeCycle;
    public String socialStructure;
    public String lifeSpan;

    // Observation & Location
    public LocalDate date;
    public LocalTime time;
    public String locationShortTitle;
    public String locationDescription;
}
