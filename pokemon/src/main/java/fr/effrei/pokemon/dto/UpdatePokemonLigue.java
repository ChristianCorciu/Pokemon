package fr.effrei.pokemon.dto;

import java.util.List;

public class UpdatePokemonLigue {
    private String name;
    private List<String> trainerIds;

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(List<String> trainerIds) {
        this.trainerIds = trainerIds;
    }
}
