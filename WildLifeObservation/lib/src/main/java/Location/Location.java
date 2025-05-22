package Location;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import Observation.Observation;


@Entity
public class Location {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID wird automatisch erstellt - Kerim
	    private Long lNr; // Primärschlüssel
	    private String shortTitle; 
	    private String description; 

	    // braucht man da wir zw. Animal und Location N:M beziehung haben - Kerim
	    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	    private List<Observation> observations;

	    public Location() {}

	    public Location(String shortTitle, String description) {
	        this.shortTitle = shortTitle;
	        this.description = description;
	    }

		public Long getlNr() {
			return lNr;
		}

		public void setlNr(Long lNr) {
			this.lNr = lNr;
		}

		public String getShortTitle() {
			return shortTitle;
		}

		public void setShortTitle(String shortTitle) {
			this.shortTitle = shortTitle;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Observation> getObservations() {
			return observations;
		}

		public void setObservations(List<Observation> observations) {
			this.observations = observations;
		}
}
