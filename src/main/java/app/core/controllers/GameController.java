package app.core.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.enums.PlayEnum;
import app.core.services.GameService;
import app.core.util.GameData;

@CrossOrigin
@RestController
@RequestMapping("/api/game")
public class GameController {
	@Autowired
	GameService gameService;
	
	
	@PostMapping("/createGame")
	public GameData createGame(@RequestHeader String token, @RequestBody GameData gameDetails) {
		try {
			return gameService.createGame(token, gameDetails);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"CREATE GAME FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/closeGame/{gameName}")
	public String closeGame(@RequestHeader String token, @PathVariable String gameName) {
		try {
			return gameService.closeGame(token, gameName);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"CLOSE GAME FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/startGame/{name}")
	public GameData startGame(@RequestHeader String token, @PathVariable String name) {
		try {
			return gameService.startGame(token, name);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"START GAME FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/join/{gameName}")
	public GameData joinGame(@RequestHeader String token, @PathVariable String gameName) {
		try {
			return gameService.joinGame(token, gameName);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"LOGOUT FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/leave/{gameName}")
	public GameData leaveGame(@RequestHeader String token, @PathVariable String gameName) {
		try {
			return gameService.leaveGame(token, gameName);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"LOGOUT FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/getOpenGames")
	public List<String> getOpenGames(@RequestHeader String token){
		try {
			return gameService.getOpenGames();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"GET GAMES FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/allowReveal/{reveal}")
	public boolean allowReveal(@RequestHeader String token, @PathVariable boolean reveal) {
		try {
			return gameService.allowReveal(token, reveal);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"PING FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/pingGame")
	public GameData pingGame(@RequestHeader String token) {
		try {
			return gameService.pingGame(token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"PING FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/play/{play}/{amount}")
	public GameData play(@RequestHeader String token, @PathVariable PlayEnum play, @PathVariable int amount) {
		try {
			return gameService.play(token,play,amount);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"PLAY FAILED - "+ e.getLocalizedMessage());
		}
	}

}
