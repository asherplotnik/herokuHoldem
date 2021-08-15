package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.services.PlayerService;
import app.core.util.PlayerPayload;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/player")
public class playerController {
	@Autowired
	PlayerService playerService;
	
	@GetMapping("/login")
	public PlayerPayload login(@RequestHeader String password, @RequestHeader String email, HttpServletRequest req) {
		System.out.println(password+email);
		try {
			return playerService.login(email, password, req.getRemoteAddr());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"LOGIN FAILED - "+ e.getLocalizedMessage());
		}
				
	}
	
	@PostMapping("/signup")
	public PlayerPayload signup(@RequestBody PlayerPayload payload, HttpServletRequest req) {
		System.out.println(payload);
		try {
			return playerService.createPlayer(payload, req.getRemoteAddr());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"SIGNUP FAILED - "+ e.getLocalizedMessage());
		}
				
	}
	
	@GetMapping("/logout")
	public String logout(@RequestHeader String token) {
		try {
			return playerService.logout(token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"LOGOUT FAILED - "+ e.getLocalizedMessage());
		}
	}
	@PostMapping("/buy")
	public int buy(@RequestHeader String token) {
		try {
			return playerService.buy(token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"LOGOUT FAILED - "+ e.getLocalizedMessage());
		}
	}
	
	

}
