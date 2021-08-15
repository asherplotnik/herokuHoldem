package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

	Optional<Player> findByEmailAndPassword(String email, String password);

	Optional<Player> findByEmail(String email);
	
	Player getById(int id);
	Optional<Player> findByIpAddress(String ipAddress);
}
