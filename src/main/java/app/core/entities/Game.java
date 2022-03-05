package app.core.entities;

import app.core.enums.CardEnum;
import app.core.enums.PlayEnum;
import app.core.enums.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Entity
public class Game {

	public Game() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int admin;
	@Column(unique = true)
	private String name;
	private LocalDateTime start;
	private LocalDateTime end;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private int pot;
	@OneToMany(mappedBy = "currentGame", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Player> players = new ArrayList<>();
	@ElementCollection(targetClass = Integer.class)
	private List<Integer> activePlayers = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = CardEnum.class)
	private List<CardEnum>  deck = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = CardEnum.class)
	private List<CardEnum>  flop; 
	private int playerTurn;
	private int dealer;
	@Enumerated(EnumType.STRING)
	private PlayEnum lastPlay;
	private int lastRaised;
	private int smallBlindBet;
	private int bigBlindBet;
	
	public List<Player> getPlayersPublicData(Player currentPlayer){
		List<Player> list = new ArrayList<>();
		boolean notAllFold = false;
		if (status == StatusEnum.SHOWDOWN) {
			for (Player player : players) {
				if (!player.isWinner() && player.getLastAct()!=PlayEnum.FOLD) {
					notAllFold = true;
					break;
				}
			}
		}
		
		Player publicPlayer;
		for (Player player : players) {
			publicPlayer = new Player();
			publicPlayer.setName(player.getName());
			publicPlayer.setWallet(player.getWallet());
			publicPlayer.setLastAct(player.getLastAct());
			publicPlayer.setLastAmount(player.getLastAmount());
			publicPlayer.setWinner(player.isWinner());			
			publicPlayer.setId(player.getId());
			boolean reveal = false;
			if (this.status == StatusEnum.WAITING && this.activePlayers.size() > 1){
				reveal = true;
			} else if (this.activePlayers.size() == 1 && player.isAllowReveal()){
				reveal = true;
			}
			if (player == currentPlayer || reveal) {
				publicPlayer.setCard1(player.getCard1());
				publicPlayer.setCard2(player.getCard2());
				publicPlayer.setCompareString(player.getCompareString());
			}
			if (player.isWinner() && (player.isAllowReveal() || notAllFold)) {
				publicPlayer.setCard1(player.getCard1());
				publicPlayer.setCard2(player.getCard2());
				publicPlayer.setCompareString(player.getCompareString());
			}
			list.add(publicPlayer);
		}	
		return list;
	}
	
	public int getSmallBlindBet() {
		return smallBlindBet;
	}


	public List<Integer> getActivePlayers() {
		return activePlayers;
	}

	public void setActivePlayers(List<Player> players) {
		this.activePlayers = new ArrayList<>();
		for (Player currPlayer : players) {
			if (currPlayer.getLastAct()!= PlayEnum.FOLD)
				this.activePlayers.add(currPlayer.getId());
		}
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



	public int getLastRaised() {
		return lastRaised;
	}


	public void setLastRaised(int lastRaised) {
		this.lastRaised = lastRaised;
	}


	public void addPlayer(Player player) {
		if (players == null) {
			players = new ArrayList<>();
		}
		player.setCurrentGame(this);
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		player.setCurrentGame(null);
		players.remove(player);
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

	public void setDealer(int place) {
		this.dealer = place;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<CardEnum> getDeck() {
		return deck;
	}

	public void setDeck(List<CardEnum> deck) {
		this.deck = deck;
	}

	public List<CardEnum> getFlop() {
		return flop;
	}

	public void setFlop(List<CardEnum> flop) {
		this.flop = flop;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public void initDeck() {
		this.deck = new ArrayList<>(List.of(CardEnum.C2, CardEnum.C3, CardEnum.C4, CardEnum.C5, CardEnum.C6,
				CardEnum.C7, CardEnum.C8, CardEnum.C9, CardEnum.C10, CardEnum.C11, CardEnum.C12, CardEnum.C13,
				CardEnum.C14, CardEnum.D2, CardEnum.D3, CardEnum.D4, CardEnum.D5, CardEnum.D6, CardEnum.D7, CardEnum.D8,
				CardEnum.D9, CardEnum.D10, CardEnum.D11, CardEnum.D12, CardEnum.D13, CardEnum.D14, CardEnum.H2,
				CardEnum.H3, CardEnum.H4, CardEnum.H5, CardEnum.H6, CardEnum.H7, CardEnum.H8, CardEnum.H9, CardEnum.H10,
				CardEnum.H11, CardEnum.H12, CardEnum.H13, CardEnum.H14, CardEnum.S2, CardEnum.S3, CardEnum.S4,
				CardEnum.S5, CardEnum.S6, CardEnum.S7, CardEnum.S8, CardEnum.S9, CardEnum.S10, CardEnum.S11,
				CardEnum.S12, CardEnum.S13, CardEnum.S14));
		Collections.shuffle(deck);
		this.flop = new ArrayList<>();
	}

	public void deal() {
		setStatus(StatusEnum.DEAL);
		initDeck();
		for (Player player : players) {
			player.setCard1(deck.remove(0));
		}
		for (Player player : players) {
			player.setCard2(deck.remove(0));
		}
	}

	public void flop() {
		offsetPlayers();
		setLastRaised(getFirstPlayer());
		setLastPlay(PlayEnum.CHECK);
		setStatus(StatusEnum.FLOP);
		setPlayerTurn(getFirstPlayer());
		flop = new ArrayList<>();
		flop.add(deck.remove(0));
		flop.add(deck.remove(0));
		flop.add(deck.remove(0));
		
	}

	public void turn() {
		offsetPlayers();
		setLastRaised(getFirstPlayer());
		setLastPlay(PlayEnum.CHECK);
		setStatus(StatusEnum.TURN);
		setPlayerTurn(getFirstPlayer());
		flop.add(deck.remove(0));
	}

	public void river() {
		offsetPlayers();
		setLastRaised(getFirstPlayer());
		setLastPlay(PlayEnum.CHECK);
		setStatus(StatusEnum.RIVER);
		setPlayerTurn(getFirstPlayer());
		flop.add(deck.remove(0));
	}
	
	public void resetPlayers() {
		for (Player player : players) {
			player.setLastAmount(0);
			player.setLastAct(null);
			player.setCard1(null);
			player.setCard2(null);
			player.setLastConnected(null);
			player.setCompareString(null);
			player.setWinner(false);
			player.setLastConnected(LocalDateTime.now());
		}
	}	
	
	public void offsetPlayers() {
		for (Player player : players) {
			player.setLastAmount(0);
			player.setLastAct(null);
			player.setLastConnected(LocalDateTime.now());
		}
	}	
	public int getFirstPlayer() {
		if (getDealer() < this.activePlayers.size()-1)
			return getDealer()+1;
		else 
			return 0;
	}
	public int getNextPlayer(int player) {
		if(player < activePlayers.size()-1)
			return player+1;
		else 
			return 0;
	}
	public int getPreviousPlayer(int player) {
		if(player > 0)
			return player-1;
		else 
			return activePlayers.size()-1;
	}

	public void showdown() {
		setStatus(StatusEnum.WAITING);
		if (activePlayers.size()==1) {
			System.out.println("----------WINNER------------");
			if (players.get(0).isWinner()) {
				players.get(0).setWallet(players.get(0).getWallet() + (int)(pot*0.95));
				System.out.println(players.get(0).getName());
			}
			return;
		}
		List<Integer> winners = new ArrayList<>();
		String bestHand = "0";
		System.out.println("[" + flop.get(0).toString() + "," + flop.get(1).toString() + "," + flop.get(2).toString()
				+ "," + flop.get(3).toString() + "," + flop.get(4).toString() + "]");
		for (Player player : players) {
			String eval = "";
			List<String> hand = new ArrayList<>(List.of(player.getCard1().toString(), player.getCard2().toString(),
					flop.get(0).toString(), flop.get(1).toString(), flop.get(2).toString(), flop.get(3).toString(),
					flop.get(4).toString()));
//			hand = new ArrayList<>(List.of("D3","S4","H2","C2","S2","D8","C8"));
			var numbered = hand.stream()
					.map(currentHand -> Integer.parseInt(currentHand.substring(1)))
					.collect(toList());

			var distinct = numbered.stream()
					.distinct()
					.sorted(Collections.reverseOrder())
					.collect(toList());

			Map<Integer, Integer> evalMap = new HashMap<>();

			for (Integer s : distinct) {
				evalMap.put(s, Collections.frequency(numbered, s));
			}

			Comparator<Map.Entry<Integer, Integer>> comparingByKey = Map.Entry.comparingByKey();
			Comparator<Map.Entry<Integer, Integer>> comparingByValue = Map.Entry.comparingByValue();

			evalMap = evalMap.entrySet().stream()
					.sorted(Collections.reverseOrder(comparingByValue.thenComparing(comparingByKey)))
					.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

			System.out.println("---------------------------");
			System.out.println(player.getName());
			System.out.println("[" + hand.get(0) + "," + hand.get(1) + "]");

			Map.Entry<Integer,Integer> entrySet = evalMap.entrySet().iterator().next();
			Integer cardSequence = entrySet.getKey();
			Integer sequence = entrySet.getValue();

			List<String> flush;
			int straight;
			int straightFlush;
			List<Integer> redacted = new ArrayList<>(distinct);
			redacted.sort(Collections.reverseOrder());
			switch (sequence) {
			case 1:
				if ((flush = checkFlush(hand)) != null) {
					if ((straightFlush = checkStraightFlush(flush)) > 0) {
						eval = "9(Straight Flush)-" + straightFlush;
						player.setCompareString("9-"+straightFlush);
					} else {
						eval = "6(Flush)-" + flush;
						player.setCompareString("6-"+convertFlush(flush));
					}
				} else {
					if ((straight = checkStraight(numbered)) > 0) {
						eval = "5(Straight)-" + straight;
						player.setCompareString("5-" + straight);
			
					} else {
						eval = "1(High Number)-[" + numbered.get(0) + ", " + numbered.get(1) + ", " + numbered.get(2)
								+ ", " + numbered.get(3) + ", " + numbered.get(4) + "]";
						player.setCompareString("1-" + numbered.get(0) + "-" + numbered.get(1) + "-" + numbered.get(2)
						+ "-" + numbered.get(3) + "-" + numbered.get(4));
					}
				}
				break;
			case 2:
				int[] pairArr = { 0, 0 };
				int pairCount = 0;
				for (Map.Entry<Integer, Integer> entry : evalMap.entrySet()) {
					if (entry.getValue() == 2) {
						pairCount++;
						if (pairCount < 3) {
							redacted.remove(entry.getKey());
							pairArr[pairCount - 1] = entry.getKey();
						}
					}
				}
				if (pairCount > 2) {
					eval = "3(Two Pairs)-[" + pairArr[0] + "," + pairArr[1] + "]-[" + redacted.get(0) + "]";
					player.setCompareString("3-" + pairArr[0] + "-" + pairArr[1] + "-" + redacted.get(0));
				} else {
					if ((flush = checkFlush(hand)) != null) {
						if ((straightFlush = checkStraightFlush(flush)) > 0) {
							eval = "9(Straight Flush)-" + straightFlush;
							player.setCompareString("9-"+straightFlush);
						} else {
							eval = "6(Flush)-" + flush;
							player.setCompareString("6-"+convertFlush(flush));
						}
					} else {
						if ((straight = checkStraight(numbered)) > 0) {
							eval = "5(Straight)-" + straight;
							player.setCompareString("5-" + straight);
						} else {
							if (pairCount == 2) {
								eval = "3(Two Pairs)-[" + pairArr[0] + "," + pairArr[1] + "]-[" + redacted.get(0) + "]";
								player.setCompareString("3-"  + pairArr[0]+ "-" + redacted.get(0));
							}
							else {
								eval = "2(One Pair)-[" + pairArr[0] + "]-[" + redacted.get(0) + "," + redacted.get(1)
										+ "," + redacted.get(2) + "]";
								player.setCompareString("2-"+ pairArr[0]+"-"+ redacted.get(0)+"-"+redacted.get(1)+"-"+redacted.get(2));
							}
						}
					}
				}
				break;
			case 3:
				int triple = 0;
				int pair = 0;
				int tripleCount = 0;
				for (Map.Entry<Integer, Integer> entry : evalMap.entrySet()) {
					if (entry.getValue() == 3) {
						tripleCount++;
						redacted.remove(entry.getKey());
						if (tripleCount < 2) {
							triple = entry.getKey();
						} else {
							pair = entry.getKey();
						}
					}
					if (entry.getValue() == 2) {
						pair = entry.getKey();
						break;
					}
				}
				if (tripleCount > 1) {
					eval = "7(Full House)-[" + triple + "]-[" + pair + "]";
					player.setCompareString("7-" + triple + "-" + pair);
				} else {
					if (pair != 0) {
						eval = "7(Full House)-[" + triple + "]-[" + pair + "]";
						player.setCompareString("7-" + triple + "-" + pair);
					} else {
						if ((flush = checkFlush(hand)) != null) {
							if ((straightFlush = checkStraightFlush(flush)) > 0) {
								eval = "9(Straight Flush)-" + straightFlush;
								player.setCompareString("9-" + straightFlush);
							} else {
								eval = "6(Flush)-" + flush;
								player.setCompareString("6-" + convertFlush(flush));
							}
						} else {
							if ((straight = checkStraight(numbered)) > 0) {
								eval = "5(Straight)-" + straight;
								player.setCompareString("5-" + straight);
							} else {
								eval = "4(Triple)-[" + triple + "]-[" + redacted.get(0) + ","+ redacted.get(1) +"]";
								player.setCompareString("4-" + triple + "-" + redacted.get(0) + "-"+ redacted.get(1));
							}
						}
					}
				}
				break;
			case 4:
				System.out.println(redacted);
				System.out.println(cardSequence);
				redacted.remove(cardSequence);
				eval = "8(Quartet)-[" + cardSequence + "]-[" + redacted.get(0) + "]";
				player.setCompareString("8-" + cardSequence + "-" + redacted.get(0));
				break;
			}			
			System.out.println(eval);
			int result = compareResult(bestHand,player.getCompareString());
			switch (result){
				case 0:
					winners.add(players.indexOf(player));
					break;
				case 1:
					winners.clear();
					winners.add(players.indexOf(player));
					bestHand = player.getCompareString();
					break;
				case -1:
					break;
			}
							
			System.out.println(player.getCompareString());
		}
		System.out.println("----------WINNERS------------");
		
		for (int el : winners) {
			System.out.println(players.get(el));
			players.get(el).setWinner(true);
			players.get(el).setWallet(players.get(el).getWallet() + (int)(pot*0.95)/winners.size());
			players.get(el).setAllowReveal(true);
		}
		System.out.println("----------WINNERS------------");
	}

	private int compareResult(String bestHand, String compareString) {
		List<Integer> bestList = new ArrayList<>();
		List<Integer> compareList = new ArrayList<>();
		for(int i=0;i<bestHand.length();i++) {
			if (bestHand.charAt(i)!='-' && i < bestHand.length()-1 && bestHand.charAt(i+1)!='-') {
				bestList.add(Integer.parseInt(bestHand.substring(i,i+2)));
				i++;
			} else if (bestHand.charAt(i)!='-') {
				bestList.add(Integer.parseInt(bestHand.substring(i,i+1)));
			}
		}
		for(int i=0;i<compareString.length();i++) {
			if (compareString.charAt(i)!='-' && i < compareString.length()-1 && compareString.charAt(i+1)!='-') {
				compareList.add(Integer.parseInt(compareString.substring(i,i+2)));
				i++;
			} else if (compareString.charAt(i)!='-') {
				compareList.add(Integer.parseInt(compareString.substring(i,i+1)));
			}
		}
		int count  = 0;
		while (count < bestList.size()) {
			if(compareList.get(count) > bestList.get(count) ) {
				return 1;
			} else if(compareList.get(count) < bestList.get(count)) {
				return -1;
			}
			count++;
		}
		return 0;
	}

	private int checkStraight(List<Integer> numbered) {
		if (numbered.size() < 5) {
			return -1;
		}
		List<Integer> sorted = new ArrayList<>(new HashSet<>(numbered));
		sorted.sort(Collections.reverseOrder());
		int i;
		int count = 1;
		int highest = sorted.get(0);
		if (sorted.get(0) == 14) {
			for (i = 0; i < sorted.size() - 1; i++) {
				if (sorted.get(i) - sorted.get(i + 1) == 1) {
					count++;
					if (count > 4) {
						return highest;
					}
				} else {
					sorted.set(0, 1);
					sorted.sort(Collections.reverseOrder());
					break;
				}
			}
		}
		count = 1;
		highest = sorted.get(0);
		for (i = 0; i < sorted.size() - 1; i++) {
			if (sorted.get(i) - sorted.get(i + 1) == 1) {
				count++;
				if (count > 4) {
					return highest;
				}
			} else {
				count = 1;
				highest = sorted.get(i + 1);
			}
		}
		return -1;
	}

	private int checkStraightFlush(List<String> hand) {
		List<Integer> numbered = new ArrayList<>();
		for (String s : hand) {
			numbered.add(Integer.parseInt(s.substring(1)));
		}
		return checkStraight(numbered);
	}

	private List<String> checkFlush(List<String> hand) {
		List<Integer> numList = new ArrayList<>();
		List<String> list = new ArrayList<>();
		String[] arr = { "C", "D", "H", "S" };
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				if (hand.get(j).substring(0, 1).equals(arr[i])) {
					numList.add(Integer.parseInt(hand.get(j).substring(1)));
				}
			}

			if (numList.size() > 4) {
				numList.sort(Collections.reverseOrder());
				for (Integer num : numList) {
					list.add(arr[i] + num);
				}
				return list;
			}
			numList = new ArrayList<>();
			list = new ArrayList<>();
		}
		return null;
	}
	
	private String convertFlush(List<String> list) {
		StringBuilder str = new StringBuilder();
		for (String el: list) {
			str.append(el.substring(1)).append("-");
		}
		return str.substring(0,str.length()-1);
	}

}
