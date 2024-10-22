package fr.effrei.pokemon.dto;

import java.util.List;

public class CreatePokemonArene {

    private String name;
    private List<String> trainerIds;
    private String championId;

    // Getters et setters
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

    public String getChampionId() {
        return championId;
    }

    public void setChampionId(String championId) {
        this.championId = championId;
    }
}
