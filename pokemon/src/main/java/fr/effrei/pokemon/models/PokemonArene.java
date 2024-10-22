package fr.effrei.pokemon.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PokemonArene {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToMany
    private List<Trainer> trainers = new ArrayList<>();

    @ManyToOne
    private Trainer champion;

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Trainer getChampion() {
        return champion;
    }

    public void setChampion(Trainer champion) {
        this.champion = champion;
    }
}
