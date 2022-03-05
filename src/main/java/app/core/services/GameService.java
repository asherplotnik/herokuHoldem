package app.core.services;

import app.core.entities.Game;
import app.core.entities.Player;
import app.core.enums.PlayEnum;
import app.core.enums.StatusEnum;
import app.core.exceptions.HoldemException;
import app.core.repositories.GameRepository;
import app.core.repositories.PlayerRepository;
import app.core.security.JwtUtil;
import app.core.util.GameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Value("${inactiveMaxTime:300}")
    int inactiveMaxTime;

    public GameData createGame(String token, GameData gameData) throws HoldemException {
        try {
            int admin = jwtUtil.extractId(token);
            Optional<Player> player = playerRepository.findById(admin);
            if (player.isEmpty()) {
                throw new HoldemException("- invalid player");
            }
            Game game = new Game();
            game.setName(gameData.getName());
            game.setSmallBlindBet(gameData.getSmallBlindBet());
            game.setBigBlindBet(game.getSmallBlindBet() * 2);
            game.setAdmin(admin);
            game.setDealer(-1);
            game.setStatus(StatusEnum.WAITING);
            gameRepository.save(game);
            return joinGame(token, gameData.getName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public GameData startGame(String token, String name) throws HoldemException {
        try {
            int admin = jwtUtil.extractId(token);
            Optional<Player> playerOpt = playerRepository.findById(admin);
            if (playerOpt.isEmpty()) {
                throw new HoldemException("- need more players");
            }
            Game game = gameRepository.findByName(name);
            if (game.equals(null)) {
                throw new HoldemException("- invalid game.");
            }
            if (game.getAdmin() != admin) {
                throw new HoldemException("- invalid admin.");
            }
            if (game.getPlayers().size() < 2) {
                throw new HoldemException("- need more players.");
            }
            if (game.getStatus() != StatusEnum.WAITING) {
                throw new HoldemException("- game already started.");
            }
            if (game.getDealer() < game.getPlayers().size() - 1) {
                game.setDealer(game.getDealer() + 1);
            } else {
                game.setDealer(0);
            }

            game.resetPlayers();
            game.setActivePlayers(game.getPlayers());
            game.deal();
            if (game.getActivePlayers().size() == 2) {
                game.getPlayers().get(game.getDealer())
                        .setWallet(game.getPlayers().get(game.getDealer()).getWallet() - game.getSmallBlindBet());
                game.getPlayers().get(game.getDealer()).setLastAct(PlayEnum.BET);
                game.getPlayers().get(game.getDealer()).setLastAmount(game.getSmallBlindBet());
                game.getPlayers().get(game.getNextPlayer(game.getDealer()))
                        .setWallet(game.getPlayers().get(game.getNextPlayer(game.getDealer())).getWallet()
                                - game.getBigBlindBet());
                game.getPlayers().get(game.getNextPlayer(game.getDealer())).setLastAct(PlayEnum.RAISE);
                game.getPlayers().get(game.getNextPlayer(game.getDealer())).setLastAmount(game.getBigBlindBet());
                game.setPlayerTurn(game.getDealer());
                game.setLastRaised(game.getNextPlayer(game.getDealer()));
            } else {
                game.getPlayers().get(game.getNextPlayer(game.getDealer()))
                        .setWallet(game.getPlayers().get(game.getNextPlayer(game.getDealer())).getWallet()
                                - game.getSmallBlindBet());
                game.getPlayers().get(game.getNextPlayer(game.getDealer())).setLastAct(PlayEnum.BET);
                game.getPlayers().get(game.getNextPlayer(game.getDealer())).setLastAmount(game.getSmallBlindBet());
                game.getPlayers().get(game.getNextPlayer(game.getNextPlayer(game.getDealer()))).setWallet(
                        game.getPlayers().get(game.getNextPlayer(game.getNextPlayer(game.getDealer()))).getWallet()
                                - game.getBigBlindBet());
                game.getPlayers().get(game.getNextPlayer(game.getNextPlayer(game.getDealer())))
                        .setLastAct(PlayEnum.RAISE);
                game.getPlayers().get(game.getNextPlayer(game.getNextPlayer(game.getDealer())))
                        .setLastAmount(game.getBigBlindBet());
                game.setLastRaised(game.getNextPlayer(game.getNextPlayer(game.getDealer())));
                game.setPlayerTurn(game.getNextPlayer(game.getNextPlayer(game.getNextPlayer(game.getDealer()))));
            }
            game.setLastPlay(PlayEnum.RAISE);
            game.setPot(game.getBigBlindBet() + game.getSmallBlindBet());
            return sendData(playerOpt.get(), game);
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public String closeGame(String token, String gameName) throws HoldemException {
        try {
            Game game = gameRepository.findByName(gameName);
            if (game == null) {
                throw new HoldemException("- invalid game.");
            }
            int admin = jwtUtil.extractId(token);
            Optional<Game> optGame = gameRepository.findById(game.getId());
            if (optGame.isEmpty()) {
                throw new HoldemException("- game not found");
            }
            if (optGame.get().getAdmin() != admin) {
                throw new HoldemException("- you are not admin");
            }
            for (Player player : game.getPlayers()) {
                player.setCurrentGame(null);
                player.setLastAmount(0);
                player.setLastAct(null);
                player.setCard1(null);
                player.setCard2(null);
                player.setLastConnected(null);
                player.setCompareString(null);
                player.setWinner(false);
                player.setAllowReveal(false);
            }
            game.setActivePlayers(new ArrayList<>());
            gameRepository.deleteById(game.getId());
            return game.getName() + " closed.";
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public GameData joinGame(String token, String name) throws HoldemException {
        try {
            Game game = gameRepository.findByName(name);
            if (game == null) {
                throw new HoldemException("- invalid game.");
            }
            int id = jwtUtil.extractId(token);
            Optional<Player> optPlayer = playerRepository.findById(id);
            if (optPlayer.isEmpty()) {
                throw new HoldemException("- player not found.");
            }
            if (game.getPlayers().contains(optPlayer.get())) {
                throw new HoldemException("- player already joined this game.");
            }
            if (game.getStatus() != StatusEnum.WAITING) {
                throw new HoldemException("- Game already started.");
            }
            game.addPlayer(optPlayer.get());
            return sendData(optPlayer.get(), game);
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public GameData leaveGame(String token, String name) throws HoldemException {
        try {
            Game game = gameRepository.findByName(name);
            if (game == null) {
                throw new HoldemException("- invalid game.");
            }
            int id = jwtUtil.extractId(token);
            Optional<Player> optPlayer = playerRepository.findById(id);
            if (optPlayer.isEmpty()) {
                throw new HoldemException("- player not found.");
            }
            Player player = optPlayer.get();
            if (!game.getPlayers().contains(optPlayer.get())) {
                throw new HoldemException("- player is not in this game.");
            }
            game.removePlayer(optPlayer.get());
            if (game.getPlayers().size() < 2) {
                game.setStatus(StatusEnum.WAITING);
                game.setFlop(null);
                game.setPot(0);
            }
            if (game.getPlayerTurn() == game.getActivePlayers().size() - 1) {
                game.setPlayerTurn(0);
            }
            game.getActivePlayers().remove(player.getId());
            player.setLastAmount(0);
            player.setLastAct(null);
            player.setCard1(null);
            player.setCard2(null);
            player.setLastConnected(null);
            player.setCompareString(null);
            player.setWinner(false);
            player.setAllowReveal(false);
            return sendData(player, game);
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    private GameData sendData(Player player, Game game) {
        GameData gameData = new GameData();
        gameData.setPlayers(game.getPlayersPublicData(player));
        gameData.setActivePlayers(game.getActivePlayers());
        gameData.setAdmin(game.getAdmin());
        gameData.setFlop(game.getFlop());
        gameData.setId(game.getId());
        gameData.setName(game.getName());
        gameData.setPlayerTurn(game.getPlayerTurn());
        gameData.setPot(game.getPot());
        gameData.setStart(game.getStart());
        gameData.setStatus(game.getStatus());
        gameData.setLastPlay(game.getLastPlay());
        gameData.setLastRaised(game.getLastRaised());
        return gameData;
    }

    public GameData pingGame(String token) throws HoldemException {
        try {
            Optional<Player> player = playerRepository.findById(jwtUtil.extractId(token));
            if (player.isEmpty()) {
                throw new HoldemException("- invalid player");
            }
            Game game = gameRepository.findByName(player.get().getCurrentGame().getName());
            if (game == null) {
                throw new HoldemException("- invalid Game name");
            }
            if (game.getStatus() != StatusEnum.WAITING) {
                if (player.get().equals(getCurrentPlayer(game))
                        && player.get().getLastAct() == PlayEnum.FOLD) {
                    nextTurn(game);
                }
                checkConnection(game);
            }
            return sendData(player.get(), game);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public void checkConnection(Game game) {
        try {
            int currentTurnId = game.getActivePlayers().get(game.getPlayerTurn());
            Player currentPlayer = playerRepository.getById(currentTurnId);
            if (currentPlayer.getLastAct() != PlayEnum.FOLD) {
                Duration duration = Duration.between(currentPlayer.getLastConnected(), LocalDateTime.now());
                if (duration.getSeconds() > inactiveMaxTime) {
                    play(currentPlayer, PlayEnum.FOLD, 0);
                }
            }
        } catch (HoldemException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public List<String> getOpenGames() {
        List<Game> games = gameRepository.findByStatus(StatusEnum.WAITING);
        //List<Game> games = gameRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Game game : games) {
            list.add(game.getName());
        }
        return list;
    }

    public GameData play(String token, PlayEnum play, int amount) throws HoldemException {
        try {
            Optional<Player> playerOpt = playerRepository.findById(jwtUtil.extractId(token));
            if (playerOpt.isEmpty()) {
                throw new HoldemException("- invalid player");
            }
            Player player = playerOpt.get();
            Game game = gameRepository.findByName(player.getCurrentGame().getName());
            if (!CollectionUtils.isEmpty(game.getPlayers())) {
                System.out.println("-----------------");
                System.out.println(game.getPlayers());
                System.out.println("-----------------");
            } else {
                throw new HoldemException("- invalid Game.");
            }
            if (!Objects.equals(player.getId(), game.getActivePlayers().get(game.getPlayerTurn()))) {
                throw new HoldemException("- not your turn");
            }
            if (player.getWallet() < amount) {
                throw new HoldemException("- not enough money in wallet");
            }
            if (player.getLastAct() == PlayEnum.FOLD) {
                throw new HoldemException("you folded in this hand");
            }
            int id = game.getActivePlayers().get(game.getLastRaised());
            int oldAmount = playerRepository.getById(id).getLastAmount();
            switch (play) {
                case BET:
                    if (game.getLastPlay() != PlayEnum.CHECK) {
                        throw new HoldemException("can't bet, can call or raise");
                    }
                    player.setWallet(player.getWallet() - amount);
                    player.setLastAmount(player.getLastAmount() + amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.BET);
                    game.setLastPlay(PlayEnum.BET);
                    game.setLastRaised(game.getActivePlayers().indexOf(player.getId()));
                    break;
                case CHECK:
                    if (game.getLastPlay() != PlayEnum.CHECK) {
                        throw new HoldemException("- can't check, can call or raise");
                    }
                    player.setLastAct(PlayEnum.CHECK);
                    game.setLastPlay(PlayEnum.CHECK);
                    player.setLastAmount(0);
                    break;
                case RAISE:
                    if (amount <= oldAmount)
                        throw new HoldemException("amount not enough");
                    player.setWallet(player.getWallet() - amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.RAISE);
                    game.setLastPlay(PlayEnum.RAISE);
                    player.setLastAmount(player.getLastAmount() + amount);
                    game.setLastRaised(game.getActivePlayers().indexOf(player.getId()));
                    break;
                case CALL:
                    if (amount != oldAmount)
                        throw new HoldemException("amount not correct");
                    player.setWallet(player.getWallet() - amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.CALL);
                    game.setLastPlay(PlayEnum.CALL);
                    player.setLastAmount(player.getLastAmount() + amount);
                    break;
                case FOLD:
                    game.getActivePlayers().remove(player.getId());
                    player.setLastAct(PlayEnum.FOLD);
                    if (game.getActivePlayers().size() == 1) {
                        setWinner(game);
                    }
                default:
            }
            player.setLastConnected(LocalDateTime.now());
            nextTurn(game);
            return sendData(player, game);
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    public GameData play(Player connectedPlayer, PlayEnum play, int amount) throws HoldemException {
        try {
            Optional<Player> playerOpt = playerRepository.findById(connectedPlayer.getId());
            if (playerOpt.isEmpty()) {
                throw new HoldemException("- invalid player");
            }
            Player player = playerOpt.get();
            Game game = gameRepository.findByName(player.getCurrentGame().getName());
            if (game == null) {
                throw new HoldemException("- invalid Game.");
            }
            if (!Objects.equals(player.getId(), game.getActivePlayers().get(game.getPlayerTurn()))) {
                throw new HoldemException("- not your turn");
            }
            if (player.getWallet() < amount) {
                throw new HoldemException("- not enough money in wallet");
            }
            if (player.getLastAct() == PlayEnum.FOLD) {
                throw new HoldemException("you folded in this hand");
            }
            int id = game.getActivePlayers().get(game.getLastRaised());
            int oldAmount = playerRepository.getById(id).getLastAmount();
            switch (play) {
                case BET:
                    if (game.getLastPlay() != PlayEnum.CHECK) {
                        throw new HoldemException("can't bet, can call or raise");
                    }
                    player.setWallet(player.getWallet() - amount);
                    player.setLastAmount(player.getLastAmount() + amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.BET);
                    game.setLastPlay(PlayEnum.BET);
                    game.setLastRaised(game.getActivePlayers().indexOf(player.getId()));
                    break;
                case CHECK:
                    if (game.getLastPlay() != PlayEnum.CHECK) {
                        throw new HoldemException("- can't check, can call or raise");
                    }
                    player.setLastAct(PlayEnum.CHECK);
                    game.setLastPlay(PlayEnum.CHECK);
                    player.setLastAmount(0);
                    break;
                case RAISE:
                    if (amount <= oldAmount)
                        throw new HoldemException("amount not enough");
                    player.setWallet(player.getWallet() - amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.RAISE);
                    game.setLastPlay(PlayEnum.RAISE);
                    player.setLastAmount(player.getLastAmount() + amount);
                    game.setLastRaised(game.getActivePlayers().indexOf(player.getId()));
                    break;
                case CALL:
                    if (amount != oldAmount)
                        throw new HoldemException("amount not correct");
                    player.setWallet(player.getWallet() - amount);
                    game.setPot(game.getPot() + amount);
                    player.setLastAct(PlayEnum.CALL);
                    game.setLastPlay(PlayEnum.CALL);
                    player.setLastAmount(player.getLastAmount() + amount);
                    break;
                case FOLD:
                    game.getActivePlayers().remove(player.getId());
                    player.setLastAct(PlayEnum.FOLD);
                    if (game.getActivePlayers().size() == 1) {
                        setWinner(game);
                    }
                default:
            }
            player.setLastConnected(LocalDateTime.now());
            nextTurn(game);
            playerRepository.save(player);
            return sendData(player, game);
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }

    private void nextTurn(Game game) throws HoldemException {
        boolean lastTurn = ((game.getPlayerTurn() == (game.getLastRaised() - 1) && game.getLastRaised() != 0)
                || (game.getLastRaised() == 0 && game.getPlayerTurn() == (game.getActivePlayers().size() - 1)));
        if (game.getPlayerTurn() < (game.getActivePlayers().size() - 1)) {
            game.setPlayerTurn(game.getPlayerTurn() + 1);
        } else {
            game.setPlayerTurn(0);
        }
        //printStatusDebug("STATUS:",game, lastTurn);
        if (lastTurn) {
            if (game.getLastPlay() != PlayEnum.BET && game.getLastPlay() != PlayEnum.BET) {
                switch (game.getStatus()) {
                    case DEAL:
                        game.flop();
                        break;
                    case FLOP:
                        game.turn();
                        break;
                    case TURN:
                        game.river();
                        break;
                    case RIVER:
                        game.showdown();
                        break;
                    default:
                }

            }
        }
        Player player = getCurrentPlayer(game);
        player.setLastConnected(LocalDateTime.now());
    }

    public void setWinner(Game game) {
        Player player = playerRepository.getById(game.getActivePlayers().get(0));
        player.setWinner(true);
        player.setAllowReveal(false);
        game.showdown();
    }

    public boolean allowReveal(String token, boolean reveal) throws HoldemException {
        try {
            Optional<Player> player = playerRepository.findById(jwtUtil.extractId(token));
            if (player.isEmpty()) {
                throw new HoldemException("- invalid player");
            }
            player.get().setAllowReveal(reveal);
            return reveal;
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }

    }

    public Player getCurrentPlayer(Game game) throws HoldemException {
        try {
            return playerRepository.getById(game.getActivePlayers().get(game.getPlayerTurn()));
        } catch (Exception e) {
            throw new HoldemException(e.getLocalizedMessage());
        }
    }
}
