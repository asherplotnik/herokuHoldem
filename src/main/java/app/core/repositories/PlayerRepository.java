package app.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

	Optional<Player> findByEmailAndPassword(String email, String password);

	Optional<Player> findByEmail(String email);
	
	Player getById(int id);
	List<Player> findByIpAddress(String ipAddress);
}
