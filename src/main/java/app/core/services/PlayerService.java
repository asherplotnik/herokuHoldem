package app.core.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Player;
import app.core.exceptions.HoldemException;
import app.core.repositories.PlayerRepository;
import app.core.security.JwtUtil;
import app.core.util.PlayerPayload;

@Service
@Transactional
public class PlayerService {
	@Autowired 
	PlayerRepository playerRepository;
	@Autowired
	JwtUtil jwtUtil;
	
	public PlayerPayload login(String email, String Password, String ipAddress) throws HoldemException{
		try {
			Optional<Player> opt = playerRepository.findByEmailAndPassword(email,Password);
			if (opt.isEmpty()){
				throw new HoldemException("- Player not found");
			}
			String currGame = null;
			if (opt.get().getCurrentGame()!=null) {
				currGame = opt.get().getCurrentGame().getName();
				
			}
			String token = jwtUtil.generateToken(opt.get().getEmail(), opt.get().getName(),opt.get().getId(),ipAddress);
			
			return new PlayerPayload(opt.get().getId(), opt.get().getName(),opt.get().getEmail(),opt.get().getPassword(),token, currGame, opt.get().getWallet());
		} catch(Exception e) {
			throw new HoldemException(e.getLocalizedMessage());
		}
	}
	
	public PlayerPayload createPlayer(PlayerPayload player, String ipAddress) throws HoldemException {
		try {
			Optional<Player> opt = playerRepository.findByEmail(player.email);
			if (opt.isPresent()){
				throw new HoldemException("- Player email already Exists");
			}
			Player dbPlayer = new Player();
			dbPlayer.setName(player.name);
			dbPlayer.setEmail(player.email);
			dbPlayer.setPassword(player.password);
			dbPlayer.setWallet(1000);
			dbPlayer  = playerRepository.save(dbPlayer);
			player.id = dbPlayer.getId();
			player.token = jwtUtil.generateToken(player.email, player.name,player.id, ipAddress);
			player.wallet = dbPlayer.getWallet();
			return player;
		} catch (Exception e) {
			throw new HoldemException(e.getLocalizedMessage());
		}
	}
	
	public void deletePlayer(int id) throws HoldemException {
		try {
			playerRepository.deleteById(id);
		} catch (Exception e) {
			throw new HoldemException(e.getLocalizedMessage());
		}
	}

	public String logout(String token) throws HoldemException {
		try {
			int id = jwtUtil.extractId(token);
			Optional<Player> player =  playerRepository.findById(id);
			if(player.isEmpty()) {
				throw new HoldemException("ERROR");
			}
			resetPlayer(player.get());
			return "logged out";
		} catch (Exception e) {
			throw new HoldemException(e.getLocalizedMessage());
		}
	}
	
	public void resetPlayer(Player player) {
		player.getCurrentGame().removePlayer(player);
		player.setCurrentGame(null);
		player.setCard1(null);
		player.setCard2(null);
		player.setCompareString(null);
		player.setLastAct(null);
		player.setWinner(false);
	}

	public int buy (String token) throws HoldemException {
		try {
			int id = jwtUtil.extractId(token);
			Optional<Player> player =  playerRepository.findById(id);
			if(player.isEmpty()) {
				throw new HoldemException("ERROR");
			}
			player.get().setWallet(player.get().getWallet()+1000);
			return player.get().getWallet();
		} catch (Exception e) {
			throw new HoldemException(e.getLocalizedMessage());
		}
	}

}
