package fr.effrei.pokemon.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PokemonLigue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany
    private List<Trainer> trainers; // Liste des entraîneurs participant à la ligue

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
}
