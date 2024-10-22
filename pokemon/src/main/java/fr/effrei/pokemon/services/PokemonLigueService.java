package fr.effrei.pokemon.services;

import fr.effrei.pokemon.dto.CreatePokemonLigue;
import fr.effrei.pokemon.dto.UpdatePokemonLigue;
import fr.effrei.pokemon.models.PokemonLigue;
import fr.effrei.pokemon.models.Trainer;
import fr.effrei.pokemon.repositories.PokemonLigueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonLigueService {

    private final PokemonLigueRepository pokemonLigueRepository;
    private final TrainerService trainerService;

    @Autowired
    public PokemonLigueService(PokemonLigueRepository pokemonLigueRepository, TrainerService trainerService) {
        this.pokemonLigueRepository = pokemonLigueRepository;
        this.trainerService = trainerService;
    }

    public List<PokemonLigue> findAll() {
        return pokemonLigueRepository.findAll();
    }

    public PokemonLigue findById(String id) {
        return pokemonLigueRepository.findById(id).orElse(null);
    }

    public void save(CreatePokemonLigue ligBody) {
        PokemonLigue pokemonLigue = new PokemonLigue();
        pokemonLigue.setName(ligBody.getName());
        // On récupère la liste des ids des dreseurs du body postman
        List<String> trainerIds = ligBody.getTrainerIds();
        String championId = ligBody.getChampionId();
        // On déclare une nouvelle liste des dresseurs
        List<Trainer> trainersAAjouter = new ArrayList<>();
        Trainer champion = null;

        if (trainerIds != null) {
            // pour chaque id de dresseurs dans ma liste de dresseurs
            for (String trainerId : trainerIds) {
                if (trainerId != null && !trainerId.isEmpty()) {
                    // je récupere le dresseur avec l'id courant
                    Trainer trainer = trainerService.findById(trainerId);
                    // si le dresseur existe et est a celui du champion
                    if (trainer != null) {
                        if (trainerId.equals(championId)) {
                            //Je le declare en temp que champion
                            trainer.setChampion(true);
                            champion = trainer;
                        } else {
                            trainer.setChampion(false);
                        }
                        trainersAAjouter.add(trainer);
                    }
                }
            }
        }
        pokemonLigue.setTrainers(trainersAAjouter);
        pokemonLigueRepository.save(pokemonLigue);
    }




    @Transactional
    public void update(String id, UpdatePokemonLigue ligueBody) {
        PokemonLigue ligueAModifier = findById(id);
        if (ligueAModifier != null) {
            if (ligueBody.getName() != null) {
                ligueAModifier.setName(ligueBody.getName());
            }
            if (ligueBody.getTrainerIds() != null && !ligueBody.getTrainerIds().isEmpty()) {
                List<Trainer> trainers = new ArrayList<>();
                List<String> trainerIds = ligueBody.getTrainerIds();
                for (String trainerId : trainerIds) {
                    Trainer trainer = trainerService.findById(trainerId);
                    if (trainer != null) {
                        trainers.add(trainer);
                    }
                }
                ligueAModifier.setTrainers(trainers);
            }
            pokemonLigueRepository.save(ligueAModifier);
        }
    }
    public void delete(String id) {
        pokemonLigueRepository.deleteById(id);
    }

}
