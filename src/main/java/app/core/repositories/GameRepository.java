package app.core.repositories;

import app.core.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer>{

	Game findByName(String name);
	List<Game> findByStatus(StatusEnum statues);
}
