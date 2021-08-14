package app.core.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import app.core.entities.Player;
import app.core.enums.CardEnum;
import app.core.enums.PlayEnum;
import app.core.enums.StatusEnum;

public class GameData {
	public GameData() {	
	}
	
	private Integer id;
	private int admin;
	private String name;
	private LocalDateTime start;
	private LocalDateTime end;
	private StatusEnum status;
	private int pot;
	private List<Player> players;
	private List<Integer> ActivePlayers;
	private List<CardEnum>  flop = new ArrayList<CardEnum>();
	private int playerturn;
	private int dealer;
	private PlayEnum lastPlay;
	private int lastRaised;
	private int smallBlindBet;
	private int bigBlindBet;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public List<Integer> getActivePlayers() {
		return ActivePlayers;
	}
	public void setActivePlayers(List<Integer> activePlayers) {
		ActivePlayers = activePlayers;
	}
	public int getLastRaised() {
		return lastRaised;
	}
	public void setLastRaised(int lastRaised) {
		this.lastRaised = lastRaised;
	}
	public int getSmallBlindBet() {
		return smallBlindBet;
	}
	public void setSmallBlindBet(int smallBlindBet) {
		this.smallBlindBet = smallBlindBet;
	}
	public int getBigBlindBet() {
		return bigBlindBet;
	}
	public void setBigBlindBet(int bigBlindBet) {
		this.bigBlindBet = bigBlindBet;
	}
	public PlayEnum getLastPlay() {
		return lastPlay;
	}
	public void setLastPlay(PlayEnum lastPlay) {
		this.lastPlay = lastPlay;
	}
	public int getDealer() {
		return dealer;
	}
	public void setDealer(int dealer) {
		this.dealer = dealer;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public int getPot() {
		return pot;
	}
	public void setPot(int pot) {
		this.pot = pot;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public List<CardEnum> getFlop() {
		return flop;
	}
	public void setFlop(List<CardEnum> flop) {
		this.flop = flop;
	}
	public int getPlayerturn() {
		return playerturn;
	}
	public void setPlayerturn(int playerturn) {
		this.playerturn = playerturn;
	}
	
	
}
