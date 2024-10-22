package fr.effrei.pokemon.repositories;

import fr.effrei.pokemon.models.Pokemon;
import fr.effrei.pokemon.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer,String> {
}
