package fr.effrei.pokemon.controller;

import fr.effrei.pokemon.dto.CreatePokemonLigue; // DTO pour la création d'une ligue
import fr.effrei.pokemon.dto.UpdatePokemonLigue; // DTO pour la mise à jour d'une ligue
import fr.effrei.pokemon.models.PokemonLigue;
import fr.effrei.pokemon.services.PokemonLigueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ligues")
public class PokemonLigueController {

    private final PokemonLigueService pokemonLigueService;

    @Autowired
    public PokemonLigueController(PokemonLigueService pokemonLigueService) {
        this.pokemonLigueService = pokemonLigueService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonLigue>> findAll() {
        return new ResponseEntity<>(pokemonLigueService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonLigue> findById(@PathVariable String id) {
        PokemonLigue ligue = pokemonLigueService.findById(id);
        if (ligue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ligue, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreatePokemonLigue ligue) {
        pokemonLigueService.save(ligue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdatePokemonLigue ligueBody) {
        PokemonLigue ligue = pokemonLigueService.findById(id);
        if (ligue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pokemonLigueService.update(id, ligueBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        PokemonLigue ligue = pokemonLigueService.findById(id);
        if (ligue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pokemonLigueService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
