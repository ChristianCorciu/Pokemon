package fr.effrei.pokemon.repositories;

import fr.effrei.pokemon.models.PokemonLigue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonLigueRepository extends JpaRepository<PokemonLigue, String>{
}
