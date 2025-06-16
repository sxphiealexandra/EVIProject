package Animal; //Alexandra

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import Genus.Genus;

/**
 * Entity-Klasse für Tiere, die beobachtet werden.
 * Enthält Eigenschaften wie Alter, Gewicht, Bild etc. - Alexandra
 */

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID wird automatisch erzeugt
    private long id;

    private String gender;
    private int estimatedAge;
    private double estimatedSize;
    private double estimatedWeight;

    @Lob
    private byte[] image; // Optionales Bild als Binärdaten zum uploaden

    @ManyToOne
    @JoinColumn(name = "genus_id") // Fremdschlüssel-Spalte in Animal-Tabelle
    private Genus genus;

    public Animal() {
        // Standardkonstruktor für JPA
    }

    // Optionaler Konstruktor ohne ID (weil automatisch generiert)
    public Animal(String gender, int estimatedAge, double estimatedSize, double estimatedWeight, Genus genus) {
        this.gender = gender;
        this.estimatedAge = estimatedAge;
        this.estimatedSize = estimatedSize;
        this.estimatedWeight = estimatedWeight;
        this.genus = genus;
    }

    // Getter und Setter
    public long getId() {
        return id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Genus getGenus() {
        return genus;
    }

    public void setGenus(Genus genus) {
        this.genus = genus;
    }
    
    public boolean hasImage() {
        return image != null && image.length > 0;
    }
}