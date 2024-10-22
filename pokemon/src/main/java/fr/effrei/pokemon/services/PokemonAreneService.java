package fr.effrei.pokemon.services;

import fr.effrei.pokemon.models.PokemonArene;
import fr.effrei.pokemon.models.Trainer;
import fr.effrei.pokemon.repositories.PokemonAreneRepository;
import fr.effrei.pokemon.dto.CreatePokemonArene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonAreneService {

    private final PokemonAreneRepository pokemonAreneRepository;
    private final TrainerService trainerService;

    @Autowired
    public PokemonAreneService(PokemonAreneRepository pokemonArenaRepository, TrainerService trainerService) {
        this.pokemonAreneRepository = pokemonArenaRepository;
        this.trainerService = trainerService;
    }

    public List<PokemonArene> findAll() {
        return pokemonAreneRepository.findAll();
    }

    public PokemonArene findById(String id) {
        return pokemonAreneRepository.findById(id).orElse(null);
    }

    public void save(CreatePokemonArene arenaBody) {
        PokemonArene pokemonArena = new PokemonArene();
        pokemonArena.setName(arenaBody.getName());
        List<String> trainerIds = arenaBody.getTrainerIds();
        List<Trainer> trainersToAdd = new ArrayList<>();
        if (trainerIds != null) {
            for (String trainerId : trainerIds) {
                Trainer trainer = trainerService.findById(trainerId);
                if (trainer != null) {
                    trainersToAdd.add(trainer);
                }
            }
        }
        String championId = arenaBody.getChampionId();
        if (championId != null) {
            Trainer champion = trainerService.findById(championId);
            if (champion != null) {
                champion.setChampion(true);
                trainersToAdd.add(champion);
                pokemonArena.setChampion(champion);
            }
        }
        pokemonArena.setTrainers(trainersToAdd);
        pokemonAreneRepository.save(pokemonArena);
    }
    public void update(String id, CreatePokemonArene arenaBody) {
        PokemonArene pokemonArena = findById(id);
        //On verifie si le nom de l'arene et nul et si c'est le cas on set le nom de l'arene
        if (pokemonArena != null) {
            if (arenaBody.getName() != null) {
                pokemonArena.setName(arenaBody.getName());
            }
            //On verifie si l'arene possede des dresseurs
            if (arenaBody.getTrainerIds() != null) {
                List<Trainer> newTrainers = new ArrayList<>();
                //Pour chaques dresseurs dans ma liste de dresseurs
                for (String trainerId : arenaBody.getTrainerIds()) {
                    // je récupere le dresseur avec l'id courant
                    Trainer trainer = trainerService.findById(trainerId);
                    if (trainer != null) {
                        //et ajoute le dresseur
                        newTrainers.add(trainer);
                    }
                }
                pokemonArena.setTrainers(newTrainers);
            }
            String championId = arenaBody.getChampionId();
            if (championId != null) {
                Trainer champion = trainerService.findById(championId);
                if (champion != null) {
                    champion.setChampion(true);
                    pokemonArena.setChampion(champion);
                }
            }

            pokemonAreneRepository.save(pokemonArena);
        }
    }

    public void delete(String id) {
        pokemonAreneRepository.deleteById(id);
    }
}
