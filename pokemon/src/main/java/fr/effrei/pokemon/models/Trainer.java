package fr.effrei.pokemon.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany
    private List<Pokemon> team;

    private boolean isChamp;

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

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public boolean isChampion() {
        return isChamp;
    }

    public void setChampion(boolean champion) {
        isChamp = champion;
    }
}
