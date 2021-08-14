package app.core.util;

public class PlayerPayload {
	
	public PlayerPayload(Integer id, String name, String email, String password, String token, String currentGame, int wallet) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.token = token;
		this.currentGame = currentGame;
		this.wallet = wallet;
	}
	public Integer id;
	public String name;
	public String email;
	public String password;
	public String currentGame;
	public String token;
	public int wallet;
	

	
	
}
