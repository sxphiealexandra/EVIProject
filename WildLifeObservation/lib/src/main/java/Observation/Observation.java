package Observation;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

import Animal.Animal;
import Location.Location;

/**
 * Observation bildet die N:M-Beziehung zwischen Animal und Location ab.
 * Enthält Infos wie Beobachtungsdatum.
 */
@Entity
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Datum der Beobachtung mit import java.time.LocalDate -Alexandra
    
    private LocalTime time; // Zeit der Beobachtung mit import java.time.LocalTime - Alexandra 

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Observation() {}

    public Observation(LocalDate date, LocalTime time, Animal animal, Location location) {
        this.date = date;
        this.time = time;
        this.animal = animal;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
       
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public LocalTime getTime() {
        return time;
    }


    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
