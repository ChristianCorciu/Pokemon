package fr.effrei.pokemon.controller;

import fr.effrei.pokemon.dto.CreatePokemonArene;
import fr.effrei.pokemon.models.PokemonArene;
import fr.effrei.pokemon.services.PokemonAreneService;
import fr.effrei.pokemon.services.PokemonAreneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arenes")
public class PokemonAreneController {

    private final PokemonAreneService pokemonAreneService;

    @Autowired
    public PokemonAreneController(PokemonAreneService pokemonAreneService, PokemonAreneService pokemonArenaService) {
        this.pokemonAreneService = pokemonAreneService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonArene>> findAll() {
        return new ResponseEntity<>(pokemonAreneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonArene> findById(@PathVariable String id) {
        PokemonArene pokemonArene = pokemonAreneService.findById(id);
        if (pokemonArene == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pokemonArene, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreatePokemonArene arenaBody) {
        pokemonAreneService.save(arenaBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CreatePokemonArene arenaBody) {
        PokemonArene pokemonArena = pokemonAreneService.findById(id);
        if (pokemonArena == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pokemonAreneService.update(id, arenaBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        PokemonArene pokemonArena = pokemonAreneService.findById(id);
        if (pokemonArena == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pokemonAreneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
