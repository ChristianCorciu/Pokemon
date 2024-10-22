package fr.effrei.pokemon.repositories;

import fr.effrei.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends JpaRepository<Pokemon ,String> {

}
