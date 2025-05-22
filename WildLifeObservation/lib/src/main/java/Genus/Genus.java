package Genus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

/**
 * Entität zur Repräsentation einer Tiergattung (Genus).
 * Wird von JPA (Jakarta Persistence API) zur Datenbankpersistierung verwendet.
 */
@Entity
public class Genus {

    @Id
    @GeneratedValue
    private long id; // Eindeutige ID, automatisch von der Datenbank generiert

    private String latinDesignation;       // Lateinischer Name
    private String designation;            // Deutsche Bezeichnung
    private String description;            // Beschreibung
    private String family;                 // Familie, zu der die Gattung gehört
    private String diet;                   // Ernährungstyp (z. B. Pflanzenfresser)
    private String reproductiveStrategy;   // Fortpflanzungsstrategie
    private String activeCycle;            // Aktivitätsmuster (z. B. tagaktiv)
    private String socialStructure;        // Sozialverhalten (z. B. Einzelgänger)
    private String lifeSpan;               // Typische Lebensdauer

    /**
     * Standardkonstruktor für JPA – notwendig für die automatische Instanziierung.
     */
    public Genus() {}

    /**
     * Konstruktor ohne ID, da diese automatisch generiert wird.
     */
    public Genus(String latinDesignation, String designation, String description,
                 String family, String diet, String reproductiveStrategy,
                 String activeCycle, String socialStructure, String lifeSpan) {
        this.latinDesignation = latinDesignation;
        this.designation = designation;
        this.description = description;
        this.family = family;
        this.diet = diet;
        this.reproductiveStrategy = reproductiveStrategy;
        this.activeCycle = activeCycle;
        this.socialStructure = socialStructure;
        this.lifeSpan = lifeSpan;
    }

    // Getter & Setter – notwendig für Zugriff und Bearbeitung durch Frameworks und Services

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id; // Wird z. B. beim Aktualisieren einer vorhandenen Entität genutzt
    }

    public String getLatinDesignation() {
        return latinDesignation;
    }

    public void setLatinDesignation(String latinDesignation) {
        this.latinDesignation = latinDesignation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getReproductiveStrategy() {
        return reproductiveStrategy;
    }

    public void setReproductiveStrategy(String reproductiveStrategy) {
        this.reproductiveStrategy = reproductiveStrategy;
    }

    public String getActiveCycle() {
        return activeCycle;
    }

    public void setActiveCycle(String activeCycle) {
        this.activeCycle = activeCycle;
    }

    public String getSocialStructure() {
        return socialStructure;
    }

    public void setSocialStructure(String socialStructure) {
        this.socialStructure = socialStructure;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
}