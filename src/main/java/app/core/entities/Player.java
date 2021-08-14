package app.core.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.core.enums.PlayEnum;
import app.core.enums.CardEnum;

@Entity
public class Player {
	public Player() {
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int wallet;
	private String name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private CardEnum card1;
	@Enumerated(EnumType.STRING)
	private CardEnum card2;
	@Enumerated(EnumType.STRING)
	private PlayEnum lastAct;
	private String compareString;
	private int lastAmount;
	private boolean winner = false;
	private boolean isAllowReveal;
	private LocalDateTime lastConnected;
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Game currentGame;
	
		
	public boolean isAllowReveal() {
		return isAllowReveal;
	}

	public void setAllowReveal(boolean isAllowReveal) {
		this.isAllowReveal = isAllowReveal;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean isWinner) {
		this.winner = isWinner;
	}

	public String getCompareString() {
		return compareString;
	}

	public void setCompareString(String compareString) {
		this.compareString = compareString;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CardEnum getCard1() {
		return card1;
	}
	public void setCard1(CardEnum card1) {
		this.card1 = card1;
	}
	public CardEnum getCard2() {
		return card2;
	}
	public void setCard2(CardEnum card2) {
		this.card2 = card2;
	}
	public Game getCurrentGame() {
		return currentGame;
	}
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}
	public PlayEnum getLastAct() {
		return lastAct;
	}
	public void setLastAct(PlayEnum lastAct) {
		this.lastAct = lastAct;
	}
	public int getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(int lastAmount) {
		this.lastAmount = lastAmount;
	}
	
	public LocalDateTime getLastConnected() {
		return lastConnected;
	}

	public void setLastConnected(LocalDateTime lastConnected) {
		this.lastConnected = lastConnected;
	}

	@Override
	public String toString() {
		return  name ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compareString, email, id, winner, lastAct, lastAmount, name, password, wallet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(compareString, other.compareString) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && winner == other.winner && lastAct == other.lastAct
				&& lastAmount == other.lastAmount && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && wallet == other.wallet;
	}	
	
	
	
	
}
