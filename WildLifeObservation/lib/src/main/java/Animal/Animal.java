package Animal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Animal {

		public Animal() {
			super();
		}
		public Animal(long id, String gender, int estimatedAge, double estimatedSize, double estimatedWeight) {
			super();
			this.id = id;
			this.gender = gender;
			this.estimatedAge = estimatedAge;
			this.estimatedSize = estimatedSize;
			this.estimatedWeight=estimatedWeight;
		}
		@Id 
		private long id;
		private String gender;
		private int estimatedAge;
		private double estimatedSize;
		private double estimatedWeight;
		
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
