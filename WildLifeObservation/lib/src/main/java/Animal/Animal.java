package Animal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entity-Klasse für Tiere, die beobachtet werden.
 * Enthält Eigenschaften wie Alter, Gewicht etc.
 */


@Entity
public class Animal {

		public Animal() {
			super();
		}
		
		/**
	     * Konstruktor zum Erstellen eines Tieres mit Eigenschaften.
	     * @param id eindeutige Tier-ID
	     * @param gender Geschlecht
	     * @param estimatedAge geschätztes Alter
	     * @param estimatedSize geschätzte Größe in cm
	     * @param estimatedWeight geschätztes Gewicht in kg
	     */
		
		public Animal(long id, String gender, int estimatedAge, double estimatedSize, double estimatedWeight) {
			super();
			this.id = id;
			this.gender = gender; // z. B. "male", "female", "unknown"
			this.estimatedAge = estimatedAge; // geschätztes Alter in Jahren
			this.estimatedSize = estimatedSize; // geschätzte Größe in Zentimeter
			this.estimatedWeight=estimatedWeight; // geschätztes Gewicht in Kilogramm
		}
		@Id 
		private long id;
		private String gender;
		private int estimatedAge;
		private double estimatedSize;
		private double estimatedWeight;
		
		// Getter & Setter
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getEstimatedAge() {
			return estimatedAge;
		}
		public void setEstimatedAge(int estimatedAge) {
			this.estimatedAge = estimatedAge;
		}
		public double getEstimatedSize() {
			return estimatedSize;
		}
		public void setEstimatedSize(double estimatedSize) {
			this.estimatedSize = estimatedSize;
		}
		public double getEstimatedWeight() {
			return estimatedWeight;
		}
		public void setEstimatedWeight(double estimatedWeight) {
			this.estimatedWeight = estimatedWeight;
		}
				

}
